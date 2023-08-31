import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * The Duke class represents a simple chatbot application that helps manage tasks.
 */
public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    /**
     * The main method to start the Duke chatbot.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Duke wizzer = new Duke();
        String logo = "Wiz";
        String filePath = "./data/duke.txt";
        //        wizzer.readFile();
//        List<Task> tasks = new ArrayList<>();
        try {
            tasks = readFile(filePath);
        } catch (DukeException e) {
            System.out.println(e);
        }
        System.out.println("Hello from " + logo +
                "\nWhat can I do for you?\n");
        System.out.println("--------------------------");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("--------------------------");
            try {
                wizzer.executeCommand(command, filePath);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println("--------------------------");
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------");
    }
    public static void writeLine(String filePath) throws DukeException {
        File resourceFile = new File(filePath);
        try {
            FileWriter writer = new FileWriter(resourceFile);
            for (Task task : tasks) {
                writer.write(task.toTxtString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }
    public static List<Task> readFile(String filePath) throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            File resourceFile = new File(filePath);
            Scanner myReader = new Scanner(resourceFile);
            int count = -1;
            while (myReader.hasNextLine()) {
                count += 1;
                String data = myReader.nextLine();

//                String[] parts = data.split(" \\| ");
//                String taskType = parts[0];
//                int isDone = Integer.parseInt(parts[1]);
//                String description = parts[2];
//                String date = parts.length > 3 ? parts[3] : "";

//                String[] parts = data.split("]\\[");
//                String isDone = parts[1].substring(0, 1); //"X" or " "

                //[E] | [0] | project meeting | Mon 2-4pm
                String[] parts = data.split(" \\| ");
                String taskType = parts[0].substring(1,2);
                String description = parts[2];
                String date = parts.length > 3 ? parts[3] : "";
//                String descripNtime[] = parts[1].substring(3).split("\\(");  //description to time
//                String description1 = descripNtime[0].trim();  // "project meeting"
//                String[] dates = descripNtime[1].split(" to: ");
//                String start1 = dates[0].substring(6); // "Mon 2pm"
//                String end1 = dates[1].substring(0, dates[1].length() - 1); //"4pm"
                if (taskType.equals("T")) {
                    Task currTask = new ToDo(description);
                    tasks.add(currTask);
                    if (parts[1].charAt(1) == 'X') {
                        tasks.get(count).markAsDone();
                    }
                } else if (taskType.equals("E")) {
                    String start = date.split("-")[0] + "pm";
                    String end = date.split("-")[1].trim();
                    Task currTask = new Event(description, start, end);
                    tasks.add(currTask);
                    if (parts[1].charAt(1) == 'X') {
                        tasks.get(count).markAsDone();
                    }
                } else if (taskType.equals("D")) {
                    Task currTask = new Deadline(description, date);
                    tasks.add(currTask);
                    if (parts[1].charAt(1) == 'X') {
                        tasks.get(count).markAsDone();
                    }
                }
            }
            myReader.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to find file.");
        }
    }

    /**
     * Parses and handles the user's command to perform various tasks.
     *
     * @param command The user's command to be processed.
     * @throws DukeException If the command is not recognised, or error
     */
    public void executeCommand(String command, String filePath) throws DukeException {
        String[] separateCommand = command.split(" ");
        System.out.println("--------------------------");
        System.out.println("--------------------------");
        if (command.equals("list")) {
            System.out.println("Task List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
            }
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            try {
                if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
                int taskNumber = Integer.parseInt(separateCommand[1]);
                if (command.startsWith("mark")) {
                    tasks.get(taskNumber - 1).markAsDone();
                    System.out.println(" Marked as done: ");

                } else if (command.startsWith("unmark")) {
                    tasks.get(taskNumber - 1).markAsUndone();
                    System.out.println(" Marked as not done yet: ");
                }
                writeLine(filePath);
                System.out.println("   " + tasks.get(taskNumber - 1).toString());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Invalid number");
            }
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")
                || command.startsWith("delete")) {
            if (command.startsWith("todo")) {
                try {
                    String description = command.substring(5);
                    if (description.length() == 0) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task currTask = new ToDo(description);
                    tasks.add(currTask);
                    writeLine(currTask.toTxtString());
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (command.startsWith("deadline")) {
                try {
                    String[] parts = command.split("/by");
                    String description = parts[0].substring(9).trim();
                    if (description.length() == 0) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String byID = parts[1].trim();
                    Task currTask = new Deadline(description, byID);
                    tasks.add(currTask);
                    writeLine(currTask.toTxtString());
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (command.startsWith("event")) {
                try {
                    String[] parts = command.split("/from");
                    String description = parts[0].substring(6).trim();
                    if (description.length() == 0) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] timeParts = parts[1].split("/to");
                    String start = timeParts[0].trim();
                    String end = timeParts[1].trim();
                    Task currTask = new Event(description, start, end);
                    tasks.add(currTask);
                    writeLine(currTask.toTxtString());
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
            } else if (command.startsWith("delete")) {
                try {
                    if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.size()) {
                        throw new DukeException("☹ OOPS!!! Invalid number");
                    }
                    int taskNumber = Integer.parseInt(separateCommand[1]);
                    if (command.startsWith("delete")) {
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + tasks.get(taskNumber - 1).toString());
                        tasks.remove(taskNumber - 1);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (command.startsWith("delete")) {
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println(" I've added this task:" + "\n" + "   " + tasks.get(tasks.size() - 1).toString()
                        + "\n" + " Now you have " + tasks.size() + " tasks in the list.");
            }
            writeLine(filePath);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
