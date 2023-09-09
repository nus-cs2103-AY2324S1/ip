package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class handles the interaction with the user.
 * It contains methods to print messages to the user.
 * It also contains methods to read user input.
 */
public class Ui {

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


    /**
     * Shows the welcome message to the user.
     */
    public String showWelcome() {
        return String.format("%s\nHello! I'm %s\nWhat can I do for you?",
                NAME_ART, NAME);
    }

    /**
     * Shows the loading error message to the user, when the file loads unsuccessfully.
     */
    public String showLoadingError() {
        return "OOPS!!!Something terrible happened to the data file.\n"
               + "Don't worry I will clean up the mess!";
    }

    /**
     * Shows the error message to the user.
     *
     * @param message the error message.
     */
    public String showError(String message) {
        return String.format("%s\n", message);
    }

    /**
     * Shows the exit message to the user.
     * Closes the scanner.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows the added task message to the user, that contains the task
     * detail and number of tasks in the task list.
     *
     * @param task the task to be added.
     * @param taskListSize the number of tasks in the task list.
     */
    public String showAddTask(Task task, int taskListSize) {
        return String.format("Got it. I've added this task:\n"
                           + "  %s\n"
                           + "Now you have %d tasks in the list.\n",
                task, taskListSize);
    }

    /**
     * Shows the delete task message to the user, that contains the task
     * detail and number of tasks in the task list.
     *
     * @param removedTask the task to be deleted.
     * @param taskListSize the number of tasks in the task list.
     */
    public String showDeleteTask(Task removedTask, int taskListSize) {
        return String.format("Noted. I've removed this task:%n"
                           + "  %s\n"
                           + "Now you have %d tasks in the list.%n",
                                removedTask, taskListSize);
    }

    /**
     * Shows the mark or unmark task message to the user, that contains the task detail.
     *
     * @param isMark whether to mark or unmark the task.
     * @param task the task to be marked.
     */
    public String showMarkTask(boolean isMark, String task) {
        String message = isMark
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        return String.format("%s\n  %s\n", message, task);
    }

    /**
     * Shows the manipulating all task messages to the user.
     *
     * @param keyword the keyword of the command.
     */
    public String showManipulateAllTask(String keyword) {
        return String.format("Noted. I will %s all tasks.\n", keyword);
    }

    /**
     * Shows the list task message to the user,
     * and lists all the tasks in the task list.
     *
     * @param tasks the string representation of the tasks in the task list.
     */
    public String showListTask(String[] tasks) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.length; i++) {
            strBuilder.append(String.format("%d.%s\n", i + 1, tasks[i]));
        }
        return strBuilder.toString();
    }

    /**
     * Shows the print date task message to the user,
     * and lists all the tasks that are happening on the specified date.
     *
     * @param tasksOnDate the string representation of the tasks happening on the specified date.
     * @param date the String representation of the specified date.
     */
    public String showPrintDateTask(String[] tasksOnDate, String date) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(String.format("Here are the %d tasks happening on %s:\n",
                tasksOnDate.length, date));
        for (String task : tasksOnDate) {
            strBuilder.append(String.format("  %s\n", task));
        }
        return strBuilder.toString();
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
    public String showFindTask(String[] tasksFound, String[] indices) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasksFound.length; i++) {
            strBuilder.append(String.format("%s.%s\n", indices[i], tasksFound[i]));
        }
        return strBuilder.toString();
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
        return String.format("%s\n%s", lineOne, lineTwo);
    }
}
