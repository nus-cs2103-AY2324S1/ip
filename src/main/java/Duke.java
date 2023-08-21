import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "____________________________________________________________";
    private static final TaskList list = new TaskList();

    private static enum Command {
        EXIT("bye"),
        LIST_TASKS("list"),
        ADD_TODO("todo"),
        ADD_DEADLINE("deadline"),
        ADD_EVENT("event"),
        MARK_TASK("mark"),
        UNMARK_TASK("unmark");

        private final String value;

        private Command(String value) {
            this.value = value;
        }

        public static Command fromString(String value) {
            for (Command command : Command.values()) {
                if (!command.value.equals("add") && command.value.equalsIgnoreCase(value)) {
                    return command;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            Command command = Command.fromString(parts[0]);
            String commandArgs = parts.length > 1 ? parts[1] : "";
            try {
                if (!Duke.executeCommand(command, commandArgs)) {
                    break;
                }
            } catch (DukeException de) {
                System.out.println(DIVIDER);
                System.out.println(de.getMessage());
                System.out.println(DIVIDER);
            }
        }

        scanner.close();
    }

    public static void greet() {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm LilBro!");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void exit() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void echo(String input) {
        System.out.println(DIVIDER);
        System.out.println(input);
        System.out.println(DIVIDER);
    }

    private static boolean executeCommand(Command command, String args)
            throws DukeInvalidCommandException, DukeInvalidArgumentException {
        if (command == null) {
            throw new DukeInvalidCommandException(
                    "Not sure what you're blabbering on about, please use a valid command.");
        }

        switch (command) {
            case EXIT:
                Duke.exit();
                return false;

            case LIST_TASKS:
                Duke.list.listTasks();
                break;

            case MARK_TASK:
                try {
                    Duke.list.markTaskDone(Integer.parseInt(args));
                } catch (NumberFormatException e) {
                    throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
                }
                break;

            case UNMARK_TASK:
                try {
                    Duke.list.unmarkTaskDone(Integer.parseInt(args));
                } catch (NumberFormatException e) {
                    throw new DukeInvalidArgumentException("Stop trolling me bro. Please enter a numeric index.");
                }
                break;

            case ADD_TODO:
                if (args.equals("")) {
                    throw new DukeInvalidArgumentException("You didn't specify a task to do. " +
                            "Check that you're doing \"todo {description}\".");
                }
                Duke.list.addTask(new ToDo(args));
                break;

            case ADD_DEADLINE:
                try {
                    String[] deadlineParts = args.split(" /by ", 2);
                    Duke.list.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeInvalidArgumentException(
                            "Your deadline seems to be formatted wrongly. " +
                                    "Check that you're doing: \"deadline {description} \\by {date}\".");
                }
                break;

            case ADD_EVENT:
                try {
                    String[] eventParts = args.split(" /from ", 2);
                    String description = eventParts[0];
                    String[] eventTimeParts = eventParts[1].split(" /to ", 2);
                    Duke.list.addTask(new Event(description, eventTimeParts[0], eventTimeParts[1]));
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeInvalidArgumentException(
                            "Your event seems to be formatted wrongly. " +
                                    "Check that you're doing: \"event {description} \\from {start} \\to {end}\".");
                }
                break;

            default:
                throw new DukeInvalidCommandException("I'm gonna be honest, no idea what you're saying.");
        }

        return true;
    }
}
