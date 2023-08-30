import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the UI of the application.
 */
public class Ui {
    public static final String line = "____________________________________________________________\n";
    private static final String greetingMessage = "Hello! I'm EnPassant\n"
            + "What can I do for you?\n";
    private static final String exitMessage = "Bye! Hope to see you again soon!\n";
    private static final String listMessage = "Here are the tasks in your list:\n";
    private static final String newTaskAddedMessage = "New task just dropped!\n";
    private static final String taskDeletedMessage = "Task went on vacation, never came back.\n";
    private static final String totalTaskCountMessage = "You now have %d tasks in the list!\n";
    private static final String markDoneMessage = "Great success! I have marked this task as done:\n";
    private static final String markUndoneMessage = "Very nice! I have marked this task as not done yet:\n";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for a Ui instance.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Print a message with horizontal lines above and below it.
     *
     * @param message The message to be printed.
     */
    private void printWithLines(String message) {
        out.print(line + message + line);
    }

    /**
     * Print all the Tasks in the list with their respective indices,
     * surrounded by horizontal lines.
     *
     * @param list The list containing the tasks.
     */
    public void showList(ArrayList<Task> list) {
        out.print(line + listMessage);
        for (int i = 0; i < list.size(); i++) {
            out.println((i + 1) + ". " + list.get(i));
        }
        out.print(line);
    }

    /**
     * Print the new task added along with its associated message,
     * the new count of tasks, surrounded by horizontal lines.
     *
     * @param task The new task added.
     * @param totalTasks The total count of tasks in the list.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        out.print(line + newTaskAddedMessage);
        out.print("  " + task + "\n");
        out.printf(totalTaskCountMessage, totalTasks);
        out.print(line);
    }

    /**
     * Print the task deleted along with its associated message,
     * the new count of tasks, surrounded by horizontal lines.
     *
     * @param task The deleted task.
     * @param totalTasks The total count of tasks in the list.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        out.print(line + taskDeletedMessage);
        out.print("  " + task + "\n");
        out.printf(totalTaskCountMessage, totalTasks);
        out.print(line);
    }

    /**
     * Print the task marked as done along with its associated message,
     * surrounded by horizontal lines.
     *
     * @param task The done task.
     */
    public void showTaskDone(Task task) {
        printWithLines(markDoneMessage + "  " + task + '\n');
    }

    /**
     * Print the task marked as undone along with its associated message,
     * surrounded by horizontal lines.
     *
     * @param task The undone task.
     */
    public void showTaskUndone(Task task) {
        printWithLines(markUndoneMessage + "  " + task + '\n');
    }

    /** Print the greeting message. */
    public void showGreeting() {
        printWithLines(greetingMessage);
    }

    /** Print the exit message. */
    public void showExitMessage() {
        printWithLines(exitMessage);
    }

    /**
     * Reads the text entered by the user.
     *
     * @return The line read.
     */
    public String readCommand() {
        return in.nextLine();
    }
}
