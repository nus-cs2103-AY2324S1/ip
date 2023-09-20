package linus.util;

import java.time.LocalDate;
import java.util.List;

import javafx.util.Pair;
import linus.task.Task;

/**
 * Represents a Ui.
 * A Ui object deals with interactions with the user.
 */
public class Ui {
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n"
            + "\t%s\n"
            + "Now you have %d tasks in the list.\n";
    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:\n"
            + "\t%s\n"
            + "Now you have %d tasks in the list.\n";
    private static final String MARK_TASK_MESSAGE = "Nice! I've marked this task as done:\n"
            + "\t%s\n";
    private static final String UNMARK_TASK_MESSAGE = "OK, I've marked this task as not done yet:\n"
            + "\t%s\n";
    private static final String NO_MATCHING_TASKS_MESSAGE = "There are no matching tasks in your list.";

    private static final String FIND_TASK_MESSAGE = "Here are the matching tasks in your list:\n";

    private static final String NO_TASKS_MESSAGE = "There are no tasks in your list.";
    private static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:\n";

    private static final String STATS_MESSAGE = "Tasks in the past %d days: \n";
    private static final String LOADING_ERROR_MESSAGE = "The file system experienced an unexpected error.";

    private static final String WELCOME_MESSAGE = "Hello from Linus!! \n "
            + "What can I do for you uwu <33 \n"
            + "Type 'help' to see the list of commands.";
    private static final String HELP_MESSAGE = "Here are the list of commands:\n"
            + "todo <description>\n"
            + "deadline <description> /by <date>\n"
            + "event <description> /from <date> /to <date> \n"
            + "delete <index>\n"
            + "mark <index>\n"
            + "unmark <index>\n"
            + "find <keyword>\n"
            + "list\n"
            + "help\n"
            + "bye\n";
    private StringBuilder output = null;

    public Ui() {
        output = new StringBuilder();
    }

    /**
     * Resets the output.
     */
    public void resetOutput() {
        output.setLength(0);
    }

    /**
     * Returns the output.
     *
     * @return The output.
     */
    public String getOutput() {
        return output.toString();
    }

    /**
     * Appends the given string to the output.
     *
     * @param str The string to be appended.
     */
    private void addToOutput(String str) {
        output.append(str);
    }

    /**
     * Prints a success message after a task is successfully added to the list.
     *
     * @param task Task added to list.
     * @param size Current size of list after addition.
     */
    public void printAddSuccessMessage(Task task, int size) {
        addToOutput(String.format(ADD_TASK_MESSAGE, task, size));
    }

    /**
     * Prints a success message after a task is deleted from to the list.
     *
     * @param task Task deleted from list.
     * @param size Current size of list after deletion.
     */
    public void printDeleteSuccessMessage(Task task, int size) {
        addToOutput(String.format(DELETE_TASK_MESSAGE, task, size));
    }

    /**
     * Prints a success message after a task is marked as done.
     *
     * @param task
     * @param size
     */
    public void printMarkSuccessMessage(Task task, int size) {
        addToOutput(String.format(MARK_TASK_MESSAGE, task, size));
    }

    /**
     * Prints a success message after a task is marked as not done yet.
     *
     * @param task
     * @param size
     */
    public void printUnmarkSuccessMessage(Task task, int size) {
        addToOutput(String.format(UNMARK_TASK_MESSAGE, task, size));
    }

    /**
     * Prints a success message after a task is found.
     *
     * @param tasks List of tasks found.
     */
    public void printFindSuccessMessage(List<Task> tasks) {
        if (tasks.size() == 0) {
            addToOutput(NO_MATCHING_TASKS_MESSAGE);
            return;
        }
        printList(tasks, FIND_TASK_MESSAGE);
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks
     */
    public void printList(List<Task> tasks) {
        printList(tasks, LIST_TASK_MESSAGE);
    }

    /**
     * Prints the given list with formatting.
     *
     * @param tasks   List of tasks to be printed.
     * @param message The message to be printed before printing the list.
     */
    public void printList(List<Task> tasks, String message) {
        if (tasks.size() == 0) {
            addToOutput(NO_TASKS_MESSAGE);
            return;
        }
        addToOutput(message + "\n");
        for (int i = 0; i < tasks.size(); i++) {
            int oneBasedIndex = i + 1;
            String formattedOutput = String.format("%d. %s\n", oneBasedIndex, tasks.get(i));
            addToOutput(formattedOutput);
        }
    }

    /**
     * Prints the statistics of tasks number of tasks per day).
     *
     * @param stats
     */
    public void printStats(List<Pair<LocalDate, Integer>> stats) {
        addToOutput(String.format(STATS_MESSAGE, stats.size()));
        for (int i = 0; i < stats.size(); i++) {
            int oneBasedIndex = i + 1;
            String formattedOutput = String.format("%d. %s: %d\n", oneBasedIndex, stats.get(i).getKey(),
                    stats.get(i).getValue());
            addToOutput(formattedOutput);
        }
    }

    /**
     * Prints the welcome message.
     */
    public String printWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints the exit message.
     */
    public void printExitMessage() {
        addToOutput(BYE_MESSAGE);
    }

    /**
     * Prints the help message.
     */
    public void printHelpMessage() {
        addToOutput(HELP_MESSAGE);
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        addToOutput(LOADING_ERROR_MESSAGE);
    }

    /**
     * Prints a given message with formatting.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        addToOutput(message);
    }

}
