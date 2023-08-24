import java.util.Scanner;

/**
 * The main class controlling the chatbot's actions.
 * This class should be able to keep track of tasks, and
 * send messages to the user.
 */
public class Duke {
    /**
     * Common text elements to be reused by the chatbot, such as the chatbot name,
     * and the horizontal line element.
     */
    private static final String name = "JOHNATHAN CENATOR\n";
    private static final String horizontalLine = "-------------------------------\n";

    /**
     * An array to store inputs by the user
     */
    private static String[] taskArray = new String[100];
    private static int numOfTasks = 0;

    /**
     * An enum to track the status of the chatbot
     * RUNNING, STOPPING, etc.
     * [To add more if needed]
     */
    enum Status {RUNNING, STOPPING}

    /**
     * Sends a greeting message on startup of the chatbot.
     */
    private static void greet() {
        System.out.println(horizontalLine +
                "YOU DIDN'T SEE\n" +
                name +
                "COMING DID YOU!?\n" +
                horizontalLine);
    }

    /**
     * Sends a departing message on chatbot shutdown.
     */
    private static void exit() {
        System.out.println(horizontalLine +
                "NOW GET OUTTA HERE!\n" +
                "RESPECTFULLY,\n" +
                name +
                horizontalLine);
    }

    /**
     * Repeats the user's input
     *
     * @param input the user's text input
     */
    private static void echo(String input) {
        System.out.println(horizontalLine + input + "\n" + horizontalLine);
    }

    /**
     * Lists all tasks in the task array
     */
    private static void list() {
        int count = 1;
        for (String task : taskArray) {
            if (task == null) break;
            System.out.println(count++ + ". " + task);
        }
    }

    /**
     * Appends a task to the task array
     *
     * @param task The task inputted by the user
     */
    private static void append(String task) {
        taskArray[numOfTasks++] = task;
        System.out.println(horizontalLine + "YOU WANT TO " + task + "?\nSURE, WHATEVER.\n" + horizontalLine);
    }

    public static void main(String[] args) {
        greet();
        Scanner textInput = new Scanner(System.in);
        Status botStatus = Status.RUNNING;

        while (botStatus == Status.RUNNING) {
            String nextLine = textInput.nextLine();
            switch(nextLine) {
                case "bye":
                    botStatus = Status.STOPPING;
                    break;
                case "list":
                    list();
                    break;
                default:
                    append(nextLine);
            }
        }

        exit();
    }
}
