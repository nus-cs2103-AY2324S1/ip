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
            if (command == null) {
                System.out.println("Invalid command!");
                continue;
            }

            String commandArgs = parts.length > 1 ? parts[1] : "";
            if (!Duke.executeCommand(command, commandArgs)) {
                break;
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

    private static boolean executeCommand(Command command, String args) {
        switch (command) {
            case EXIT:
                Duke.exit();
                return false;

            case LIST_TASKS:
                Duke.list.listTasks();
                break;

            case MARK_TASK:
                Duke.list.markTaskDone(Integer.parseInt(args));
                break;

            case UNMARK_TASK:
                Duke.list.unmarkTaskDone(Integer.parseInt(args));
                break;

            case ADD_TODO:
                Duke.list.addTask(new ToDo(args));
                break;

            case ADD_DEADLINE:
                String[] deadlineParts = args.split(" /by ", 2);
                Duke.list.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                break;

            case ADD_EVENT:
                String[] eventParts = args.split(" /from ", 2);
                String description = eventParts[0];
                String[] eventTimeParts = eventParts[1].split(" /to ", 2);
                Duke.list.addTask(new Event(description, eventTimeParts[0], eventTimeParts[1]));
                break;

            default:
                System.out.println("LilBro is confused by your command.");
        }

        return true;
    }
}
