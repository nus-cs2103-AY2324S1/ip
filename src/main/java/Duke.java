import java.util.Scanner;

public class Duke {
    private static final String HORZ_LINE = "____________________________________________________________";
    private static final String BOT_NAME = "SHIBA-BOT";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";

    private static final ShibaTask[] tasks = new ShibaTask[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        printGreeting();
        processUserInputs();
        printBye();
    }

    /**
     * Prints the greeting message.
     */
    private static void printGreeting() {
        printHorizontalLine();
        printWithIndent("Woof! I'm " + BOT_NAME);
        printWithIndent("What can I bark at you?");
        printHorizontalLine();
    }

    /**
     * Prints the bye message
     */
    private static void printBye() {
        printHorizontalLine();
        printWithIndent("Woof! Hope to bark at you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints a horizontal line.
     */
    private static void printHorizontalLine() {
        System.out.println(HORZ_LINE);
    }

    /**
     * Prints the given message with a single indent (space).
     * @param message The message to be printed.
     */
    private static void printWithIndent(String message) {
        printWithIndents(message, 1);
    }

    /**
     * Prints the given message with the given number of indents (spaces).
     * @param message The message to be printed.
     * @param indentCount The number of indents.
     */
    private static void printWithIndents(String message, int indentCount) {
        System.out.println(" ".repeat(indentCount) + message);
    }

    /**
     * Continually processes user input until bye command is issued
     */
    private static void processUserInputs() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] cmd = input.split(" ");

            switch (cmd[0]) {
                case LIST_COMMAND:
                    listTasks();
                    break;
                case MARK_COMMAND:
                case UNMARK_COMMAND:
                    handleMarkTask(cmd);
                    break;
                case BYE_COMMAND:
                    return;
                default:
                    addTask(input);
            }
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    private static void addTask(String task) {
        tasks[taskCount] = new ShibaTask(task);
        taskCount++;

        printHorizontalLine();
        printWithIndent("added: " + task);
        printHorizontalLine();
    }

    /**
     * Lists all the tasks in the list.
     */
    private static void listTasks() {
        printHorizontalLine();
        for (int i = 0; i < taskCount; i++) {
            printWithIndent((i + 1) + "." + tasks[i]);
        }
        printHorizontalLine();
    }

    /**
     * Performs actions to mark/unmark a task based on the input command parameters
     * @param cmd Input command parameters, split by spaces.
     */
    private static void handleMarkTask(String[] cmd) {
        if (cmd.length < 2) {
            printHorizontalLine();
            printWithIndent("Woof! Please specify a task number!");
            printHorizontalLine();
            return;
        }

        int taskNumber = Integer.parseInt(cmd[1]);
        if (taskNumber < 1 || taskNumber > taskCount) {
            printHorizontalLine();
            printWithIndent("Woof! Please specify a valid task number!");
            printHorizontalLine();
            return;
        }

        ShibaTask task = tasks[taskNumber - 1];
        if (cmd[0].equals(MARK_COMMAND)) {
            boolean res = task.markDone();
            printHorizontalLine();
            if (res) {
                printWithIndent("Woof! I've marked this task as done:");
            } else {
                printWithIndent("Woof! This task is already done!");
            }
            printWithIndent("  " + task);
            printHorizontalLine();
        } else {
            boolean res = task.markNotDone();
            printHorizontalLine();
            if (res) {
                printWithIndent("Woof! I've marked this task as not done yet:");
            } else {
                printWithIndent("Woof! You have not done this task yet!");
            }
            printWithIndents(task.toString(), 3);
            printHorizontalLine();
        }
    }
}
