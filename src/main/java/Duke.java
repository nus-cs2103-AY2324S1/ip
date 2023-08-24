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
    private static Task[] taskArray = new Task[100];
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
        System.out.print(horizontalLine +
                "YOU DIDN'T SEE\n" +
                name +
                "COMING DID YOU!?\n" +
                horizontalLine);
    }

    /**
     * Sends a departing message on chatbot shutdown.
     */
    private static void exit() {
        System.out.print(horizontalLine +
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
        System.out.print(horizontalLine);
        for (Task task : taskArray) {
            if (task == null) break;
            System.out.println(count++ + ". " + "[" + task.getStatusIcon() + "] " + task.toString());
        }
        System.out.print(horizontalLine);
    }

    /**
     * Appends a task to the task array
     *
     * @param task The task inputted by the user
     */
    private static void append(String task) {
        taskArray[numOfTasks++] = new Task(task);
        System.out.print(horizontalLine + "YOU WANT TO " + task + "?\nSURE, WHATEVER.\n" + horizontalLine);
    }

    /**
     * Attempts to mark a task in the task array
     *
     * @param toMark
     */
    private static void mark(String toMark) {
        System.out.print(horizontalLine);
        try {
            Task task = taskArray[Integer.parseInt(toMark.substring(5)) - 1];
            if (task == null) throw new NullPointerException();
            if (task.isDone) throw new IllegalArgumentException();
            System.out.println("MARKED:\n" + task);
            task.markAsDone();
        } catch (NumberFormatException e) {
            System.out.print("NOT A NUMBER IDIOT!!!\n");
        } catch (NullPointerException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IllegalArgumentException e) {
            System.out.print("ALREADY DONE BRO!\n");
        } finally {
            System.out.print(horizontalLine);
        }
    }

    /**
     * Attempts to unmark a task in the task array
     *
     * @param toUnmark the task to be unmarked
     */
    private static void unmark(String toUnmark) {
        System.out.print(horizontalLine);
        try {
            int index = Integer.parseInt(toUnmark.substring(7)) - 1;
            Task task = taskArray[index];
            if (task == null) throw new NullPointerException();
            if (!task.isDone) throw new IllegalArgumentException();
            System.out.println("UNMARKED:\n" + task);
            task.markAsUndone();
        } catch (NumberFormatException e) {
            System.out.print("NOT A NUMBER IDIOT!!!\n");
        } catch (NullPointerException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IllegalArgumentException e) {
            System.out.print("ALREADY UNDONE BRO!\n");
        } finally {
            System.out.print(horizontalLine);
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner textInput = new Scanner(System.in);
        Status botStatus = Status.RUNNING;

        while (botStatus == Status.RUNNING) {
            String nextLine = textInput.nextLine();
            if (nextLine.startsWith("mark")) {
                mark(nextLine);
                continue;
            }
            if (nextLine.startsWith("unmark")) {
                unmark(nextLine);
                continue;
            }
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
