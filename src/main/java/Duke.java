import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
    public void executeCommand(String command) throws DukeException {
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
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
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
    }

    /**
     * The main method to start the Duke chatbot.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Duke bot = new Duke();
        System.out.println("---------------------------------------------\n Hello! I'm zy\n" +
                " What can I do for you?\n---------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("---------------------------------------------");
            try {
                bot.executeCommand(command);
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
