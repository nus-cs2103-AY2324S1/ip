package duke;

import duke.task.Task;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class handles the UI of the application.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________\n";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor for a duke.Ui instance.
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
        out.print(LINE + message + LINE);
    }

    /**
     * Print all the duke.task.Task instances in the list with their respective indices,
     * surrounded by horizontal lines.
     *
     * @param list The list containing the tasks.
     */
    public void showList(TaskList list) {
        out.print(LINE + Messages.LIST_MESSAGE);
        list.printTasks();
        out.print(LINE);
    }

    /**
     * Print the new task added along with its associated message,
     * the new count of tasks, surrounded by horizontal lines.
     *
     * @param task The new task added.
     * @param totalTasks The total count of tasks in the list.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        out.print(LINE + Messages.NEW_TASK_ADDED_MESSAGE);
        out.print("  " + task + "\n");
        out.printf(Messages.TOTAL_TASK_COUNT_MESSAGE, totalTasks);
        out.print(LINE);
    }

    /**
     * Print the task deleted along with its associated message,
     * the new count of tasks, surrounded by horizontal lines.
     *
     * @param task The deleted task.
     * @param totalTasks The total count of tasks in the list.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        out.print(LINE + Messages.TASK_DELETED_MESSAGE);
        out.print("  " + task + "\n");
        out.printf(Messages.TOTAL_TASK_COUNT_MESSAGE, totalTasks);
        out.print(LINE);
    }

    /**
     * Print the task marked as done along with its associated message,
     * surrounded by horizontal lines.
     *
     * @param task The done task.
     */
    public void showTaskDone(Task task) {
        printWithLines(Messages.MARK_DONE_MESSAGE + "  " + task + '\n');
    }

    /**
     * Print the task marked as undone along with its associated message,
     * surrounded by horizontal lines.
     *
     * @param task The undone task.
     */
    public void showTaskUndone(Task task) {
        printWithLines(Messages.MARK_UNDONE_MESSAGE + "  " + task + '\n');
    }

    /** Print the greeting message. */
    public void showGreeting() {
        printWithLines(Messages.GREETING_MESSAGE);
    }

    /** Print the exit message. */
    public void showExitMessage() {
        printWithLines(Messages.EXIT_MESSAGE);
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
