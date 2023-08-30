import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

/**
 * The Duke class represents a simple chatbot application that helps manage tasks.
 */
public class Duke {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Parses and handles the user's command to perform various tasks.
     *
     * @param command The user's command to be processed.
     * @throws DukeException If the command is not recognized or encounters an error.
     */
    public void executeCommand(String command, String filename) throws DukeException {
        String[] separateCommand = command.split(" ");
        if (command.equals("list")) {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
            }
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            try {
                if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
                int taskNumber = Integer.parseInt(separateCommand[1]);
                if (command.startsWith("mark")) {
                    tasks.get(taskNumber - 1).markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                } else if (command.startsWith("unmark")) {
                    tasks.get(taskNumber - 1).markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                System.out.println("   " + tasks.get(taskNumber - 1).toString());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Invalid number");
            }
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            if (command.startsWith("todo")) {
                try {
                    String description = command.substring(5);
                    if (description.length() == 0) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new ToDo(description));
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
                    try {
                        LocalDate by = LocalDate.parse(parts[1].trim());
                        tasks.add(new Deadline(description, by));
                    } catch (DateTimeParseException e) {
                        throw new DukeException("☹ OOPS!!! Invalid date format.");
                    }
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
                    tasks.add(new Event(description, start, end));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(" Got it. I've added this task:" + "\n" + "   " + tasks.get(tasks.size() - 1).toString()
                    + "\n" + " Now you have " + tasks.size() + " tasks in the list.");
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
        }  else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        try {
            saveToFile(filename);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public void saveToFile(String filename) throws DukeException {
        File file = new File(filename);

        try {
            FileWriter writer = new FileWriter(file);

            for (Task task : tasks) {
                // Convert each task to its string representation and write to file
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but there's an error loading the file");
        }
    }

    private void loadFile(String filename) throws DukeException {
        File file = new File(filename);
        try {
            String directoryPath = file.getParent();
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Directory created: " + directoryPath);
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + filename);
            }
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1") ? true : false;
                String description = parts[2].trim();

                if (taskType.equals("T")) {
                    // Create a ToDo task
                    tasks.add(new ToDo(description, isDone));
                } else if (taskType.equals("D")) {
                    // Extract 'by' from parts[3] if applicable
                    LocalDate by = LocalDate.parse(parts[3].trim());
                    // Create a Deadline task
                    tasks.add(new Deadline(description, by, isDone));
                } else if (taskType.equals("E")) {
                    // Extract 'from' and 'to' from parts[3] and parts[4] if applicable
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    // Create an Event task
                    tasks.add(new Event(description, from, to, isDone));
                } else {
                    // Handle unsupported task type
                    System.out.println("Unsupported task type: " + taskType);
                }
            }
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but there's an error loading the file");
        }
    }




    /**
     * The main method to start the Duke chatbot.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        String filename = "./data/duke.txt";
        Duke bot = new Duke();
        try {
            bot.loadFile(filename);
        } catch (DukeException e) {
            System.out.println(e);
        }
        System.out.println("---------------------------------------------\n Hello! I'm zy\n" +
                " What can I do for you?\n---------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("---------------------------------------------");
            try {
                bot.executeCommand(command, filename);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println("---------------------------------------------");
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("---------------------------------------------\n Bye. Hope to see you again soon!" +
                "\n---------------------------------------------");
    }
}
