package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The Ui class handles interactions with the user by displaying messages and receiving input.
 */
public class Ui {

    /**
     * Displays a welcome message when the Duke application starts.
     *
     * @return The welcome message as a string.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Glenda!\nWhat can I do for you?\n";
    }

    /**
     * Displays a goodbye message when the Duke application ends.
     *
     * @return The goodbye message as a string.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays the task added and the current total number of tasks in the list.
     *
     * @param task The task that was added.
     * @param numberOfTasks The number of tasks in the list currently.
     * @return The message displaying the added task and the total number of tasks as a string.
     */
    public String showAddedTask(Task task, int numberOfTasks) {
        return "Got it. I've added this task to the list:\n"
                + task.toString()
                + "\nNow you have "
                + numberOfTasks
                + " task(s) in the list.\n";
    }

    /**
     * Displays all the tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The message displaying all tasks as a string.
     */
    public String showAllTasks(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();

        if (tasks.isEmpty()) {
            // Case where there are no tasks to be displayed
            message.append("No tasks added. \n");
        }

        message.append("Here are the task(s) in your list:\n");
        for (Task task : tasks) {
            message.append(tasks.indexOf(task) + 1).append(". ").append(task).append("\n");
        }

        return message.toString();
    }

    /**
     * Displays a message indicating the task was marked as done.
     *
     * @param task The task that was marked as done.
     * @return The message indicating the completed task as a string.
     */
    public String showTaskMarkedAsDone(Task task) {
        task.markAsDone();
        return "Great! I've completed this task!\n" + task.toString() + "\n";
    }

    /**
     * Displays a message indicating the task was marked as undone.
     *
     * @param task The task that was marked undone.
     * @return The message indicating the task was marked as undone as a string.
     */
    public String showTaskMarkedAsUndone(Task task) {
        task.markAsUndone();
        return "Okay, I have not yet completed this task:\n" + task.toString() + "\n";
    }

    /**
     * Displays a message indicating the tasks that were deleted, and the number of tasks left in the task list.
     *
     * @param task The task that was deleted.
     * @param numberOfTasks The number of tasks left in the task list.
     * @return The message indicating the deleted task and the remaining number of tasks as a string.
     */
    public String showDeletedTask(Task task, int numberOfTasks) {
        return "Okay, I've removed this task:\n" + task.toString()
                + "\nNow you have " + numberOfTasks + " task(s) in the list.\n";
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message shown to the user.
     * @return The error message as a string.
     */
    public String showErrorMessage(String errorMessage) {
        return errorMessage + "\n";
    }

    /**
     * Displays the list of tasks matching the keyword search.
     *
     * @param tasks Tasks associated with the keyword search.
     * @return The message displaying matching tasks as a string.
     */
    public String showMatchingTasksToKeyword(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder();

        if (tasks.isEmpty()) {
            // Case where there are no matching tasks to be displayed
            message.append("No matching tasks in the list. \n");
        }

        message.append("Here are the matching task(s) in your list:\n");
        for (Task task : tasks) {
            message.append(tasks.indexOf(task) + 1).append(". ").append(task.toString()).append("\n");
        }

        return message.toString();
    }
}
