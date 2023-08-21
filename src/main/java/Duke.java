import java.util.Scanner;

public class Duke {
    private static final String HORZ_LINE = "____________________________________________________________";
    private static final String BOT_NAME = "SHIBA-BOT";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";

    private static final String[] tasks = new String[100];
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
     * Prints the given message with an indent (space).
     *
     * @param message The message to be printed.
     */
    private static void printWithIndent(String message) {
        System.out.println(" " + message);
    }

    /**
     * Continually processes user input until bye command is issued
     */
    private static void processUserInputs() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case LIST_COMMAND:
                    listTasks();
                    continue;
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
        tasks[taskCount] = task;
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
            printWithIndent((i + 1) + ". " + tasks[i]);
        }
        printHorizontalLine();
    }
}
