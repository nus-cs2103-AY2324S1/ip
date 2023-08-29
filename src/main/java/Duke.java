import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Program to run a task manager that can add, delete and mark tasks.
 *
 * @author Teo Kai Sheng
 */
public class Duke {
    public static ArrayList<Task> list = new ArrayList<Task>();
    public static void main(String[] args) {
        Path filePath = Paths.get(".", "data", "duke.txt");
        readListFromHardDisk(filePath);
        Scanner scanner = new Scanner(System.in);
        String line = "    ______________________________________________";
        System.out.println(line + "\n    Hello, I'm your task manager :)\n    What can I do for you?\n" + line);
        String[] input = scanner.nextLine().split(" ", 2);
        while (!input[0].equals("bye")) {
            String command = input[0];
            System.out.println(line);
            if (command.equals("list") ) {
                showList(input);
            } else if (command.equals("mark")) {
                markTask(input);
            } else if (command.equals("unmark")) {
                unmarkTask(input);
            } else if (command.equals("delete")) {
                deleteTask(input);
            } else if (command.equals("deadline")) {
                addDeadline(input);
            } else if (command.equals("event")) {
                addEvent(input);
            } else if (command.equals("todo")) {
                addToDo(input);
            } else {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(line);
            input = scanner.nextLine().split(" ", 2);
        }

        System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
        writeListToHardDisk(filePath);
    }

    public static void readListFromHardDisk(Path filePath) {
        try {
            // Create the directory if it doesn't exist
            Path parentDirectory = filePath.getParent();
            if (!Files.exists(parentDirectory)) {
                Files.createDirectories(parentDirectory);
                System.out.println("    Parent directories created: " + parentDirectory.toAbsolutePath());
            }

            // Create the file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("    File created: " + filePath);
            }

            // Read the existing content from the file
            BufferedReader reader = Files.newBufferedReader(filePath);
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                list.add(stringToTask(nextLine));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("    Data file corrupted.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeListToHardDisk(Path filePath) {
        try {
            // Create the directory if it doesn't exist
            Path parentDirectory = filePath.getParent();
            if (!Files.exists(parentDirectory)) {
                Files.createDirectories(parentDirectory);
                System.out.println("    Parent directories created: " + parentDirectory.toAbsolutePath());
            }
            // Create the file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("    File created: " + filePath);
            }
            // Write new content to the file
            StringBuilder toWrite = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                toWrite.append(list.get(i).taskToString());
                toWrite.append("\n");
            }
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            writer.write(toWrite.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("    An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void showList(String input[]) {
        try {
            if (!(input.length == 1 || input[1].strip().equals(""))) {
                throw new DukeException("    Did you mean list?");
            }
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(String.format("    %d.%s",
                        i + 1, list.get(i).toString()));
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void markTask(String input[]) {
        try {
            int toMark = Integer.parseInt(input[1]);
            Task task = list.get(toMark - 1);
            task.markAsDone();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println(String.format("      %s", task.toString()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., mark 1");
        }
    }
    public static void unmarkTask(String input[]) {
        try {
            int toMark = Integer.parseInt(input[1]);
            Task task = list.get(toMark - 1);
            task.markAsUndone();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println(String.format("      %s", task.toString()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., unmark 1");
        }
    }

    public static void deleteTask(String input[]) {
        try {
            int toDelete = Integer.parseInt(input[1]);
            Task task = list.get(toDelete - 1);
            System.out.println("    Noted. I've removed this task:");
            System.out.println(String.format("      %s", task.toString()));
            list.remove(toDelete - 1);
            System.out.println("    Number of tasks: " + list.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., delete 1");
        }
    }
    public static void addEvent(String input[]) {
        try {
            String[] s1 = input[1].split("/from", 2);
            String[] s2 = s1[1].split("/to", 2);
            String desc = s1[0].strip();
            String from = s2[0].strip();
            String to = s2[1].strip();
            if (desc.equals("") || from.equals("") || to.equals("")) {
                throw new DukeException("    Format: event description /from yyyy-mm-dd /to yyyy-mm-dd");
            }
            Event e = new Event(desc, LocalDate.parse(from), LocalDate.parse(to));
            list.add(e);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + e.toString());
            System.out.println("    Number of tasks: " + list.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Format: event description /from yyyy-mm-dd /to yyyy-mm-dd");
        } catch (DateTimeParseException e) {
            System.out.println("    Enter valid date yyyy-mm-dd");
        }
    }
    public static void addDeadline(String input[]) {
        try {
            String[] s = input[1].split("/by", 2);
            String desc = s[0].strip();
            String deadline= s[1].strip();
            if (desc.equals("") || deadline.equals("")) {
                throw new DukeException("    Format: deadline description /by yyyy-mm-dd");
            }
            Deadline d = new Deadline(desc, LocalDate.parse(deadline));
            list.add(d);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + d.toString());
            System.out.println("    Number of tasks: " + list.size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Format: deadline description /by yyyy-mm-dd");
        } catch (DateTimeParseException e) {
            System.out.println("    Enter valid date yyyy-mm-dd");
        }
    }
    public static void addToDo(String input[]) {
        try {
            String desc = input[1];
            if (desc.strip().equals("")) {
                throw new DukeException("    ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            ToDo t = new ToDo(desc);
            list.add(t);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + t.toString());
            System.out.println("    Number of tasks: " + list.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Task stringToTask(String s) throws DukeException {
        String[] details = s.split("[|]", 0);
        String type = details[0].strip();
        try {
            Task t;
            if (type.equals("T")) {
                t = new ToDo(details[2].strip());
            } else if (type.equals("D")) {
                t = new Deadline(details[2].strip(), LocalDate.parse(details[3].strip()));
            } else if (type.equals("E")) {
                t = new Event(details[2].strip(),
                        LocalDate.parse(details[3].strip()), LocalDate.parse(details[4].strip()));
            } else {
                throw new DukeException("    Data file corrupted.");
            }
            if (details[1].strip().equals("Y")) {
                t.markAsDone();
            }
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    Data file corrupted.");
        }
    }
}
