import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();
    private static final String LINE = "\t____________________________________________________________";

    public enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE,
        TODO, DEADLINE, EVENT,

    }

    private void greet() {
        System.out.println(LINE);
        System.out.println("\t Hello! I'm Bard.");
        System.out.println("\t What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    private void getUserInput() {
        while (true) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.trim().isEmpty()) {
                    throw new EmptyCommandException();
                }

                String[] parts = userInput.split(" ", 2);
                CommandType command = this.getCommand(parts[0]);

                switch (command) {
                    case BYE:
                        this.exit();
                        return;
                    case LIST:
                        this.showList();
                        break;
                    case MARK:
                        this.markTaskAsDone(parts);
                        break;
                    case UNMARK:
                        this.markTaskAsNotDone(parts);
                        break;
                    case TODO:
                        this.addTodo(parts);
                        break;
                    case DEADLINE:
                        this.addDeadline(parts);
                        break;
                    case EVENT:
                        this.addEvent(parts);
                        break;
                    case DELETE:
                        this.deleteTask(parts);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(LINE);
                System.out.println("\t" + e.getMessage());
                System.out.println(LINE);
                System.out.println();
            }
        }
    }

    private CommandType getCommand(String command) throws UnknownCommandException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new UnknownCommandException();
        }
    }

    private void exit() {
        System.out.println(LINE);
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void showList() {
        if (list.isEmpty()) {
            System.out.println(LINE);
            System.out.println("\t There are no tasks in your list.");
            System.out.println(LINE);
            System.out.println();
            return;
        }

        System.out.println(LINE);
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t " + (i + 1) + ". " + this.list.get(i));
        }
        System.out.println(LINE);
        System.out.println();
    }

    private void markTaskAsDone(String[] userCommandParts) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommandParts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= list.size()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = list.get(taskIndex);
            if (task.isDone) {
                System.out.println(LINE);
                System.out.println("\t ☹ OOPS!!! This task is already marked as done:\n" +
                        "\t\t" + this.list.get(taskIndex));
                System.out.println(LINE);
                System.out.println();
            } else {
                task.markAsDone();
                System.out.println(LINE);
                System.out.println("\t Nice! I've marked this task as done:\n" +
                        "\t\t" + this.list.get(taskIndex));
                System.out.println(LINE);
                System.out.println();
            }
        } catch (InvalidTaskIndexException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE);
            System.out.println("\t ☹ OOPS!!! Please provide a valid task number to mark as done.");
            System.out.println(LINE);
            System.out.println();
        }
    }

    private void markTaskAsNotDone(String[] userCommandParts) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommandParts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= list.size()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = list.get(taskIndex);
            if (!task.isDone) {
                System.out.println(LINE);
                System.out.println("\t OOPS!!! This task is already marked as not done:\n" +
                        "\t\t" + this.list.get(taskIndex));
                System.out.println(LINE);
                System.out.println();
            } else {
                task.markAsNotDone();
                System.out.println(LINE);
                System.out.println("\t OK, I've marked this task as NOT done yet:\n" +
                        "\t\t" + this.list.get(taskIndex));
                System.out.println(LINE);
                System.out.println();
            }
        } catch (InvalidTaskIndexException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE);
            System.out.println("\t ☹ OOPS!!! Please provide a valid task number to mark as not done.");
            System.out.println(LINE);
            System.out.println();
        }

    }

    private void addTodo(String[] userCommandParts) {
        try {
            if (userCommandParts.length < 2 || userCommandParts[1].trim().isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }

            String description = userCommandParts[1].trim();

            Todo newTask = new Todo(description);
            list.add(newTask);

            System.out.println(LINE);
            System.out.println("\t Got it. I've added this task:\n" +
                    "\t\t" + newTask + "\n\t Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
            System.out.println();
        } catch (EmptyDescriptionException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        }
    }

    private void addDeadline(String[] userCommandParts) {
        try {
            if (userCommandParts.length < 2) {
                throw new EmptyDescriptionException("Deadline");
            }

            String[] deadlineParts = userCommandParts[1].split("/by");
            if (deadlineParts.length < 2) {
                throw new InvalidFormatException("Please use the format: deadline <description> /by <deadline>.");
            }

            String description = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();

            Deadline newDeadline = new Deadline(description, by);
            list.add(newDeadline);
            System.out.println(LINE);
            System.out.println("\t Got it. I've added this task:\n" +
                    "\t\t" + newDeadline + "\n\t Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
            System.out.println();

        } catch (EmptyDescriptionException | InvalidFormatException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        }
    }

    private void addEvent(String[] userCommandParts) throws EmptyDescriptionException {
        try {
            if (userCommandParts.length < 2) {
                throw new EmptyDescriptionException("event");
            }

            String[] eventParts = userCommandParts[1].split("/from");
            if (eventParts.length < 2) {
                throw new InvalidFormatException("Please use the format: event <description> /from <start> /to <end>");
            }

            String description = eventParts[0].trim();
            String[] eventTime = eventParts[1].split("/to");
            if (eventTime.length < 2) {
                throw new InvalidFormatException("Please use the format: event <description> /from <start> /to <end>");
            }

            String from = eventTime[0].trim();
            String to = eventTime[1].trim();
            Event newEvent = new Event(description, from, to);

            list.add(newEvent);
            System.out.println(LINE);
            System.out.println("\t Got it. I've added this task:\n" +
                    "\t\t" + newEvent + "\n\t Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
            System.out.println();
        } catch (EmptyDescriptionException | InvalidFormatException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        }
    }

    private void deleteTask(String[] userCommandParts) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommandParts[1]) - 1;

            if (taskIndex < 0 || taskIndex >= list.size()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task removedTask = list.remove(taskIndex);

            System.out.println(LINE);
            System.out.println("\t Noted. I've removed this task:\n" +
                    "\t\t" + removedTask +
                    "\n\t Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
            System.out.println();
        } catch (InvalidTaskIndexException e) {
            System.out.println(LINE);
            System.out.println("\t" + e.getMessage());
            System.out.println(LINE);
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(LINE);
            System.out.println("\t ☹ OOPS!!! Please provide a valid task number to delete.");
            System.out.println(LINE);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.getUserInput();
    }
}