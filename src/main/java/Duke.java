import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Task> data = new ArrayList<>();

    private static void readLine(String line) {
        String[] split = line.split(" \\| ");
        String type = split[0];
        boolean isDone = split[1].equals("1");
        String description = split[2];
        Task task = null;
        if (type.equals("T")) {
            task = new ToDo(description);
        } else if (type.equals("E")) {
            task = new Event(description,
                    LocalDateTime.parse(split[3],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    LocalDateTime.parse(split[4],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } else if (type.equals("D")) {
            task = new Deadline(description, LocalDateTime.parse(split[3],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        }

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
        data.add(task);
    }

    private static void loadFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, "duke.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                readLine(line);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    private static void saveFile() {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory, "duke.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : data) {
                writer.write(task.toSaveLine());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("An error occurred with saving.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadFile();
        String logo =
                ".______     ______   .___________.\n" +
                        "|   _  \\   /  __  \\  |           |\n" +
                        "|  |_)  | |  |  |  | `---|  |----`\n" +
                        "|   _  <  |  |  |  |     |  |     \n" +
                        "|  |_)  | |  `--'  |     |  |     \n" +
                        "|______/   \\______/      |__|     \n";

        System.out.println("_________________________________________");
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");

        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println("_________________________________________");
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println((i + 1) + "." + data.get(i));
                    }
                } else if (input.contains("todo")) {
                    String subInput;
                    try {
                        subInput = input.substring(5);
                        if (subInput.trim().equals("")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                    } catch (Exception e) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    ToDo t = new ToDo(subInput);
                    data.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + (data.size()) + " tasks in the list.");
                } else if (input.contains("deadline")) {
                    String[] split = input.split(" /by ", 2);
                    if (split.length == 1) {
                        throw new DukeException("Deadlines must have a /by.");
                    } else if (split[1].trim().equals("")) {
                        throw new DukeException("/by cannot be empty.");
                    }
                    String description = split[0].substring(9);
                    if (description.trim().equals("")) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }
                    String str = split[1];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

                    Deadline t = new Deadline(description, dateTime);
                    data.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + (data.size()) + " tasks in the list.");
                } else if (input.contains("event")) {
                    String[] split = input.split(" /from ", 2);
                    if (split.length == 1) {
                        throw new DukeException("Events must have a /from and /to.");
                    }
                    String description;
                    try {
                        description = split[0].substring(6);
                        if (description.trim().equals("")) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                    } catch (Exception e) {
                        throw new DukeException("The description of an event cannot be empty.");
                    }

                    String[] duration = split[1].split(" /to ", 2);
                    if (duration.length == 1) {
                        throw new DukeException("Events must have a /from and /to.");
                    } else if (duration[0].trim().equals("") || duration[1].trim().equals("")) {
                        throw new DukeException("/from and /to cannot be empty.");
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTimeStart = LocalDateTime.parse(duration[0], formatter);
                    LocalDateTime dateTimeEnd = LocalDateTime.parse(duration[1], formatter);

                    Event t = new Event(description, dateTimeStart, dateTimeEnd);
                    data.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + (data.size()) + " tasks in the list.");
                } else if (input.contains("unmark")) {
                    if (input.length() < 7) {
                        throw new DukeException("Task number to be unmarked cannot be empty.");
                    }
                    String subInput = input.substring(7);
                    int targetIndex;
                    try {
                        targetIndex = Integer.parseInt(subInput);
                        if (targetIndex < 1 || targetIndex - 1 >= data.size()) {
                            throw new DukeException("Task number is out of range.");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Task to be unmarked must be a number.");
                    }
                    System.out.println("Ok, I've marked this task as not done yet:");
                    Task marked = data.get(targetIndex - 1);
                    marked.markAsUndone();
                    System.out.println(marked);
                } else if (input.contains("mark")) {
                    if (input.length() < 5) {
                        throw new DukeException("Task number to be marked cannot be empty.");
                    }
                    String subInput = input.substring(5);
                    int targetIndex;
                    try {
                        targetIndex = Integer.parseInt(subInput);
                        if (targetIndex < 1 || targetIndex - 1 >= data.size()) {
                            throw new DukeException("Task number is out of range.");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Task to be marked must be a number.");
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    Task marked = data.get(targetIndex - 1);
                    marked.markAsDone();
                    System.out.println(marked);
                } else if (input.contains("delete")) {
                    if (input.length() < 7) {
                        throw new DukeException("Task number to be deleted cannot be empty.");
                    }
                    String subInput = input.substring(7);
                    int targetIndex;
                    try {
                        targetIndex = Integer.parseInt(subInput);
                        if (targetIndex < 1 || targetIndex - 1 >= data.size()) {
                            throw new DukeException("Task number is out of range.");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("Task to be deleted must be a number.");
                    }
                    System.out.println("Noted. I've removed this task:");
                    Task marked = data.remove(targetIndex - 1);
                    System.out.println(marked);
                    System.out.println("Now you have " + (data.size()) + " tasks in the list.");
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println("_________________________________________");
            saveFile();
        }
    }
}
