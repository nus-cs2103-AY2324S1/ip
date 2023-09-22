package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The Ui class provides user interface functionality for interacting with the Duke application.
 * It displays messages to the user, such as welcome messages, task lists, and error messages.
 */
public class Ui {

    /**
     * Displays a goodbye message to the user.
     *
     * @return The goodbye message as a string.
     */
    public String showGoodbyeMessage() {
        StringBuilder message = new StringBuilder();
        message.append(" Bye. Hope to see you again soon!\n");
        return message.toString();
    }

    /**
     * Displays a welcome message to the user.
     *
     * @return The welcome message as a string.
     */
    public String showHiMessage() {
        StringBuilder message = new StringBuilder();
        message.append(" Hi! How can i help you this fine day?\n");
        message.append("\n If unsure of what to do just type in 'help' to see all the command possible\n");
        return message.toString();
    }

    /**
     * Displays an error message when tasks cannot be loaded from a file.
     *
     * @return The error message as a string.
     */
    public String showLoadingError() {
        StringBuilder message = new StringBuilder();
        message.append("Error loading tasks from file.\n");
        return message.toString();
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param taskList The list of tasks to display.
     * @return The list of tasks as a string.
     */
    public String showTaskList(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder();
        message.append(" Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            message.append(" ").append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        return message.toString();
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task       The task that has been added.
     * @param totalTasks The total number of tasks after adding.
     * @return The message as a string.
     */
    public String showTaskAdded(Task task, int totalTasks) {
        assert task != null : "Task cannot be null";
        StringBuilder message = new StringBuilder();
        message.append(" Got it. I've added this task:\n");
        message.append("   ").append(task).append("\n");
        message.append(" Now you have ").append(totalTasks).append(" tasks in the list.\n");
        return message.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return The message as a string.
     */
    public String showTaskMarkedAsDone(Task task) {
        assert task != null : "Task cannot be null";
        StringBuilder message = new StringBuilder();
        message.append(" Nice! I've marked this task as done:\n");
        message.append("   ").append(task).append("\n");
        return message.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return The message as a string.
     */
    public String showTaskMarkedAsNotDone(Task task) {
        assert task != null : "Task cannot be null";
        StringBuilder message = new StringBuilder();
        message.append(" OK, I've marked this task as not done yet:\n");
        message.append("   ").append(task).append("\n");
        return message.toString();
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task       The task that has been deleted.
     * @param totalTasks The total number of tasks after deletion.
     * @return The message as a string.
     */
    public String showTaskDeleted(Task task, int totalTasks) {
        assert task != null : "Task cannot be null";
        StringBuilder message = new StringBuilder();
        message.append(" Noted. I've removed this task:\n");
        message.append("   ").append(task).append("\n");
        message.append(" Now you have ").append(totalTasks).append(" tasks in the list.\n");
        return message.toString();
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param matchingTasks The list of matching tasks to display.
     * @return The matching tasks as a string.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder message = new StringBuilder();
        message.append(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            message.append(" ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
        }
        return message.toString();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     * @return The error message as a string.
     */
    public String showError(String message) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(" ").append(message).append("\n");
        return errorMessage.toString();
    }
}
