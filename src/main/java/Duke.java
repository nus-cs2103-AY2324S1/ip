import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "____________________________________________________________";
    private static final TaskList list = new TaskList();

    private static enum Command {
        EXIT("bye"),
        LIST_TASKS("list"),
        ADD_TASK("add"),
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
            return Command.ADD_TASK;
        }
    }

    public static void main(String[] args) {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            Command command = Command.fromString(parts[0]);
            String[] commandArgs;
            if (command == Command.ADD_TASK) {
                commandArgs = new String[] { input };
            } else {
                commandArgs = Arrays.copyOfRange(parts, 1, parts.length);
            }

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

    private static boolean executeCommand(Command command, String[] args) {
        switch (command) {
            case EXIT:
                Duke.exit();
                return false;

            case LIST_TASKS:
                Duke.list.listTasks();
                break;

            case MARK_TASK:
                Duke.list.markTaskDone(Integer.parseInt(args[0]));
                break;

            case UNMARK_TASK:
                Duke.list.unmarkTaskDone(Integer.parseInt(args[0]));
                break;

            default:
                Duke.list.addTask(args[0]);
        }

        return true;
    }
}
