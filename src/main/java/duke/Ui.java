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
    public void printWithLines(String message) {
        out.print(LINE + message + LINE);
    }

    /**
     * Print all the duke.task.Task instances in the list with their respective indices,
     * surrounded by horizontal lines.
     *
     * @param list The list containing the tasks.
     */
    public void showList(TaskList list) {
        out.print(LINE + Messages.MESSAGE_LIST);
        list.printTasks();
        out.print(LINE);
    }

    /**
     * Print the new task added along with its associated message,
     * the new count of tasks, surrounded by horizontal lines.
     *
     * @param task       The new task added.
     * @param totalTasks The total count of tasks in the list.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        out.print(LINE + Messages.MESSAGE_NEW_TASK_ADDED);
        out.print("  " + task + "\n");
        out.printf(Messages.MESSAGE_TOTAL_TASK_COUNT, totalTasks);
        out.print(LINE);
    }

    /**
     * Print the task deleted along with its associated message,
     * the new count of tasks, surrounded by horizontal lines.
     *
     * @param task       The deleted task.
     * @param totalTasks The total count of tasks in the list.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        out.print(LINE + Messages.MESSAGE_TASK_DELETED);
        out.print("  " + task + "\n");
        out.printf(Messages.MESSAGE_TOTAL_TASK_COUNT, totalTasks);
        out.print(LINE);
    }

    /**
     * Print the task marked as done along with its associated message,
     * surrounded by horizontal lines.
     *
     * @param task The done task.
     */
    public void showTaskDone(Task task) {
        printWithLines(Messages.MESSAGE_MARK_DONE + "  " + task + '\n');
    }

    /**
     * Print the task marked as undone along with its associated message,
     * surrounded by horizontal lines.
     *
     * @param task The undone task.
     */
    public void showTaskUndone(Task task) {
        printWithLines(Messages.MESSAGE_MARK_UNDONE + "  " + task + '\n');
    }

    /**
     * Print the greeting message.
     */
    public void showGreeting() {
        printWithLines(Messages.MESSAGE_GREETING);
    }

    /**
     * Print the exit message.
     */
    public void showExitMessage() {
        printWithLines(Messages.MESSAGE_EXIT);
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
