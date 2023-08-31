package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class handles the interaction with the user.
 * It contains methods to print messages to the user.
 * It also contains methods to read user input.
 */
public class Ui {

    protected static final String INDENT = "     ";
    protected static final String NAME = "404";
    private static final String NAME_ART =
              "               _                _               _                      \n"
            + "           _  /\\ \\            / /\\          _  /\\ \\               \n"
            + "          /\\_\\\\ \\ \\          / /  \\        /\\_\\\\ \\ \\        \n"
            + "         / / / \\ \\ \\        / / /\\ \\      / / / \\ \\ \\          \n"
            + "        / / /   \\ \\ \\      / / /\\ \\ \\    / / /   \\ \\ \\        \n"
            + "        \\ \\ \\____\\ \\ \\    /_/ /  \\ \\ \\   \\ \\ \\____\\ \\ \\ \n"
            + "         \\ \\________\\ \\   \\ \\ \\   \\ \\ \\   \\ \\________\\ \\ \n"
            + "          \\/________/\\ \\   \\ \\ \\   \\ \\ \\   \\/________/\\ \\  \n"
            + "                    \\ \\ \\   \\ \\ \\___\\ \\ \\            \\ \\ \\ \n"
            + "                     \\ \\_\\   \\ \\/____\\ \\ \\            \\ \\_\\ \n"
            + "                      \\/_/    \\_________\\/             \\/_/";
    private Scanner sc;


    /**
     * Shows the welcome message to the user.
     */
    public void showWelcome() {
        String greeting = String.format("%sHello! I'm %s\n%sWhat can I do for you?",
                INDENT, NAME, INDENT);
        System.out.println(NAME_ART);
        showLine();
        System.out.println(greeting);
        showLine();
        System.out.println();
    }

    /**
     * Shows the loading error message to the user, when the file loads unsuccessfully.
     */
    public void showLoadingError() {
        String goneWrongMessage =
                String.format("%sOOPS!!!Something terrible happened to the data file.\n"
                              + "%sDon't worry I will clean up the mess!", INDENT, INDENT);
        showLine();
        System.out.println(goneWrongMessage);
        showLine();
    }

    /**
     * Reads the user input for commands.
     *
     * @return the user input.
     */
    public String readCommand() {
        if (this.sc == null) {
            this.sc = new Scanner(System.in);
        }
        return sc.nextLine();
    }

    /**
     * Shows the error message to the user.
     *
     * @param message the error message.
     */
    public void showError(String message) {
        System.out.printf("%s%s\n", INDENT, message);
    }

    /**
     * Shows the exit message to the user.
     * Closes the scanner.
     */
    public void showExit() {
        sc.close();
        System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
    }

    /**
     * Shows the line to separate each command and respond to the user.
     */
    public void showLine() {
        String line = "    ____________________________________________________________\n"
                + "   /_____/_____/_____/_____/_____/_____/_____/_____/_____/_____/";
        System.out.println(line);
    }

    /**
     * Shows the added task message to the user, that contains the task
     * detail and number of tasks in the task list.
     *
     * @param task the task to be added.
     * @param taskListSize the number of tasks in the task list.
     */
    public void showAddTask(Task task, int taskListSize) {
        System.out.printf("%sGot it. I've added this task:\n"
                          + "%s  %s\n"
                          + "%sNow you have %d tasks in the list.\n",
                INDENT, INDENT, task, INDENT, taskListSize);
    }

    /**
     * Shows the delete task message to the user, that contains the task
     * detail and number of tasks in the task list.
     *
     * @param removedTask the task to be deleted.
     * @param taskListSize the number of tasks in the task list.
     */
    public void showDeleteTask(Task removedTask, int taskListSize) {
        System.out.printf("%sNoted. I've removed this task:%n"
                          + "%s  %s\n"
                          + "%sNow you have %d tasks in the list.%n",
                INDENT, INDENT, removedTask, INDENT, taskListSize);
    }

    /**
     * Shows the mark or unmark task message to the user, that contains the task detail.
     *
     * @param isMark whether to mark or unmark the task.
     * @param task the task to be marked.
     */
    public void showMarkTask(boolean isMark, String task) {
        String message = isMark
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        System.out.printf("%s%s\n%s  %s\n", INDENT,
                message, INDENT, task);
    }

    /**
     * Shows the manipulating all task messages to the user.
     *
     * @param keyword the keyword of the command.
     */
    public void showManipulateAllTask(String keyword) {
        System.out.printf("%sNoted. I will %s all tasks.\n", INDENT, keyword);
    }

    /**
     * Shows the list task message to the user,
     * and lists all the tasks in the task list.
     *
     * @param tasks the string representation of the tasks in the task list.
     */
    public void showListTask(String[] tasks) {
        System.out.printf("%sHere are the tasks in your list:\n", INDENT);
        for (int i = 0; i < tasks.length; i++) {
            System.out.printf("%s%d.%s\n", INDENT,
                    i + 1, tasks[i]);
        }
    }

    /**
     * Shows the print date task message to the user,
     * and lists all the tasks that are happening on the specified date.
     *
     * @param tasksOnDate the string representation of the tasks happening on the specified date.
     * @param date the String representation of the specified date.
     */
    public void showPrintDateTask(String[] tasksOnDate, String date) {
        System.out.printf("%sHere are the %d tasks happening on %s:\n",
                INDENT, tasksOnDate.length, date);
        for (String task : tasksOnDate) {
            System.out.printf("%s  %s\n", INDENT, task);
        }
    }

    /**
     * Shows the find task message to the user,
     * and lists all the tasks that contain the task keyword.
     *
     * @param tasksFound the array containing string representation
     *                   of the tasks containing the task keyword.
     * @param indices the array containing string representation for the
     *                indices of the tasks in tasksFound.
     */
    public void showFindTask(String[] tasksFound, String[] indices) {
        System.out.printf("%sHere are the matching tasks in your list:\n", INDENT);
        for (int i = 0; i < tasksFound.length; i++) {
            System.out.printf("%s%s.%s\n", INDENT, indices[i], tasksFound[i]);
        }
    }

    /**
     * Connects two Strings, such that it is uniform with formatting
     * of the output display messages (e.g., indentations).
     *
     * @param lineOne the first string. To be placed at the front.
     * @param lineTwo the second string. To be placed at the back.
     * @return the connected string.
     */
    public static String connectTwoLine(String lineOne, String lineTwo) {
        return String.format("%s\n%s%s", lineOne, INDENT, lineTwo);
    }
}
