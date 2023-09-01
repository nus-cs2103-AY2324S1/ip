import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        Path path = Paths.get("src/main/java/data/duke.txt");

        try {
            File file = path.toFile();

            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(path.toString()));

            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("\\|");
                switch (strings[0]) {
                case "T":
                    Todo todo = new Todo(strings[2], Integer.parseInt(strings[1]));
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(strings[2], LocalDate.parse(strings[3]), Integer.parseInt(strings[1]));
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(strings[2], strings[3], strings[4], Integer.parseInt(strings[1]));
                    tasks.add(event);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        File inFile = path.toFile();

        System.out.println("    Hello! I'm Arona, your Virtual Assistant.\n    What can I do for you today?\n");

        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine().toLowerCase().trim();

        while (!echo.equals("bye")) {
            try {
                String[] strings = echo.split(" ");
                if (!echo.isEmpty()) { // Skips processing when echo is empty.
                    if (echo.equals("list")) { // Lists all current tasks.
                        System.out.println("    " +
                                (tasks.size() == 0
                                        ? "Great job! No tasks right now, enjoy your day!"
                                        : (tasks.size() == 1
                                        ? "Good news! You only have one task:"
                                        : "Hello! Here is your list of tasks:")));
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("");

                    } else if (strings[0].equals("mark")) { // Marks a task.
                        if (strings.length == 1) {
                            throw new IllegalArgumentDukeException("    Whoops! Don't forget to specify the task.\n");
                        }
                        int index = Integer.valueOf(strings[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new IllegalArgumentDukeException("    Uh-oh, that task does not exist.\n");
                        }
                        Task task = tasks.get(index);
                        task.mark();

                        try {
                            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

                            BufferedReader br = new BufferedReader(new FileReader(inFile));
                            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

                            String line = null;
                            int currentLine = 0;

                            while ((line = br.readLine()) != null) {
                                if (currentLine != index) {
                                    bw.write(line);
                                    bw.newLine();
                                } else {
                                    char[] charArray = line.toCharArray();
                                    charArray[2] = '1';
                                    String modifiedLine = new String(charArray);
                                    bw.write(modifiedLine);
                                    bw.newLine();
                                }
                                currentLine++;
                            }

                            br.close();
                            bw.close();

                            inFile.delete();
                            tempFile.renameTo(inFile);

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        System.out.println("    Awesome! I've marked this task as done:");
                        System.out.println("      " + task + "\n");

                    } else if (strings[0].equals("unmark")) { // Unmarks a task.
                        if (strings.length == 1) {
                            throw new IllegalArgumentDukeException("    Whoops! Don't forget to specify the task.\n");
                        }
                        int index = Integer.valueOf(strings[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new IllegalArgumentDukeException("    Uh-oh, that task does not exist.\n");
                        }
                        Task task = tasks.get(index);
                        task.unmark();

                        try {
                            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

                            BufferedReader br = new BufferedReader(new FileReader(inFile));
                            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

                            String line = null;
                            int currentLine = 0;

                            while ((line = br.readLine()) != null) {
                                if (currentLine != index) {
                                    bw.write(line);
                                    bw.newLine();
                                } else {
                                    char[] charArray = line.toCharArray();
                                    charArray[2] = '0';
                                    String modifiedLine = new String(charArray);
                                    bw.write(modifiedLine);
                                    bw.newLine();
                                }
                                currentLine++;
                            }

                            br.close();
                            bw.close();

                            inFile.delete();
                            tempFile.renameTo(inFile);

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        System.out.println("    Sure thing! I've marked this task as not done yet:");
                        System.out.println("      " + task + "\n");

                    } else if (strings[0].equals("todo")) { // Adds a todo task.
                        if (strings.length == 1) {
                            throw new IllegalArgumentDukeException("    Oh no! You forgot to specify the task!\n");
                        }
                        String description = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
                        Todo todo = new Todo(description);
                        tasks.add(todo);

                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

                            String data = "T|0|" + description + "\n";

                            bw.write(data);
                            bw.close();

                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                        System.out.println("    Got it! I've added this task:");
                        System.out.println("      " + todo);
                        System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

                    } else if (strings[0].equals("deadline")) { // Adds a deadline task.
                        if (strings.length == 1) {
                            throw new IllegalArgumentDukeException("    Oh no! You forgot to specify the task!\n");
                        }
                        if (!echo.contains("/by")) {
                            throw new IllegalArgumentDukeException("    Whoopsie! The deadline seems a bit confused. Please use '/by' to set it.\n");
                        }
                        int index = -1;
                        for (int i = 0; i < strings.length; i++) {
                            if (strings[i].charAt(0) == '/') {
                                index = i;
                                strings[i] = strings[i].substring(1) + ":";
                                break;
                            }
                        }

                        String description = String.join(" ", Arrays.copyOfRange(strings, 1, index));
                        String by = String.join(" ", Arrays.copyOfRange(strings, index + 1, strings.length));

                        LocalDate d = null;
                        try {
                            // Attempt to parse as a LocalDate
                            d = LocalDate.parse(by);

                        } catch (DateTimeParseException e) {
                            // Handle the case where the format is invalid
                            throw new IllegalArgumentDukeException("Whoopsie! The deadline seems a bit confused. Please use a 'YYYY-MM-DD' format to set it.\n");
                        }

                        Deadline deadline = new Deadline(description, d);
                        tasks.add(deadline);

                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

                            String data = "D|0|" + description + "|" + by + "\n";

                            bw.write(data);
                            bw.close();

                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                        System.out.println("    Got it! I've added this task:");
                        System.out.println("      " + deadline);
                        System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

                    } else if (strings[0].equals("event")) { // Adds an event task.
                        if (strings.length == 1) {
                            throw new IllegalArgumentDukeException("    Oh no! You forgot to specify the event!\n");
                        }

                        if (!(echo.contains("/from") && echo.contains("/to"))) {
                            throw new IllegalArgumentDukeException("    Uh-oh! This event seems to have lost its way. Please use '/from' and '/to' to set it.\n");
                        }
                        int indexStart = -1;
                        int indexEnd = -1;
                        boolean first = true;
                        for (int i = 0; i < strings.length; i++) {
                            if (strings[i].charAt(0) == '/') {
                                if (first) {
                                    indexStart = i;
                                    first = false;
                                    strings[i] = strings[i].substring(1) + ":";
                                } else {
                                    indexEnd = i;
                                    strings[i] = strings[i].substring(1) + ":";
                                    break;
                                }
                            }
                        }
                        String description = String.join(" ", Arrays.copyOfRange(strings, 1, indexStart));
                        String from = String.join(" ", Arrays.copyOfRange(strings, indexStart, indexEnd));
                        String to = String.join(" ", Arrays.copyOfRange(strings, indexEnd, strings.length));
                        Event event = new Event(description, from, to);
                        tasks.add(event);

                        try {
                            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

                            String data = "E|0|" + description + "|" + from + "|" + to + "\n";

                            bw.write(data);
                            bw.close();

                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }

                        System.out.println("    Got it! I've added this task:");
                        System.out.println("      " + event);
                        System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

                    } else if (strings[0].equals("delete")) { // Deletes a task.
                        if (strings.length == 1) {
                            throw new IllegalArgumentDukeException("    Whoops! Don't forget to specify the task to be deleted.\n");
                        }
                        int index = Integer.valueOf(strings[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new IllegalArgumentDukeException("    Uh-oh, that task does not exist.\n");
                        }
                        Task task = tasks.get(index);
                        tasks.remove(index);

                        try {
                            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

                            BufferedReader br = new BufferedReader(new FileReader(inFile));
                            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

                            String line = null;
                            int currentLine = 0;

                            while ((line = br.readLine()) != null) {
                                if (currentLine != index) {
                                    bw.write(line);
                                    bw.newLine();
                                }
                                currentLine++;
                            }

                            br.close();
                            bw.close();

                            inFile.delete();
                            tempFile.renameTo(inFile);

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        System.out.println("    Sure thing! I've removed this task:");
                        System.out.println("      " + task + "\n");
                        System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

                    } else { // Invalid arguments
                        throw new IllegalArgumentDukeException("    Oops! I'm not quite sure what that means...\n");
                    }
                }
            } catch (IllegalArgumentDukeException e) {
                System.out.println(e.getMessage());
            } finally {
                echo = scanner.nextLine().toLowerCase().trim(); // Reads next line
            }
        }
        System.out.println("    Goodbye. See you soon!"); // Exits the programme.
    }
}

