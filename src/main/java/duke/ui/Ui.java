package duke.ui;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Represents the user interface of the Duke chatbot.
 */
public class Ui {

    /** The current status or messages displayed to the user */
    private String currentStatus = "";

    /**
     * Changes the current status by appending a message.
     *
     * @param status The message to append to the current status.
     */
    private void changeStatus(String status) {
        currentStatus += status + "\n";
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        changeStatus("Hello! I'm Bob");
        changeStatus("What can I do for you?");
        showLine();
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        changeStatus("");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        changeStatus("☹ " + errorMessage);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        changeStatus("Bye. Hope to see you again soon!");
    }


    /**
     * Displays a message indicating matching tasks found based on the keyword search.
     *
     * @param matchingTasks The list of matching tasks to display.
     */
    public void showMatchingTasks(TaskList matchingTasks) throws DukeException {
        changeStatus("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            changeStatus((i + 1) + "." + matchingTasks.get(i));
        }
    }

    /**
     * Displays a message indicating no matching tasks were found based on the keyword search.
     */
    public void showNoMatchingTasks() {
        changeStatus("No matching tasks found.");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     * @throws DukeException If an error occurs while displaying the tasks.
     */
    public void showTaskList(TaskList tasks) throws DukeException {
        changeStatus("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            changeStatus((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param tasks The list of tasks.
     * @throws DukeException If an error occurs while displaying the message.
     */
    public void showTaskAddedMessage(TaskList tasks) throws DukeException {
        changeStatus("Got it. I've added this task:");
        changeStatus(tasks.get(tasks.size() - 1).toString());
        changeStatus("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks The list of tasks.
     * @param taskIndex The index of the task that has been marked as done.
     * @throws DukeException If an error occurs while displaying the message.
     */
    public void showTaskMarkedAsDone(TaskList tasks, int taskIndex) throws DukeException {
        changeStatus("Nice! I've marked this task as done:");
        changeStatus(tasks.get(taskIndex).toString());
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param tasks The list of tasks.
     * @param taskIndex The index of the task that has been marked as not done.
     * @throws DukeException If an error occurs while displaying the message.
     */
    public void showTaskMarkedAsUndone(TaskList tasks, int taskIndex) throws DukeException {
        changeStatus("OK, I've marked this task as not done yet:");
        changeStatus(tasks.get(taskIndex).toString());
    }

    /**
     * Displays a message indicating that a task has been removed from the list.
     *
     * @param tasks The list of tasks.
     * @param removedTask The task that has been removed from the list.
     */
    public void showTaskRemoved(TaskList tasks, Task removedTask) {
        changeStatus("Noted. I've removed this task:");
        changeStatus(removedTask.toString());
        changeStatus("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays an error message indicating that tasks could not be loaded from a file.
     */
    public void showLoadingError() {
        changeStatus("Error loading tasks from file.");
    }

    /**
     * Gets the current status or messages displayed to the user and clears the status.
     *
     * @return The current status or messages displayed to the user.
     */
    public String getCurrentStatus() {
        String tmp = currentStatus;
        this.currentStatus = "";
        return tmp;
    }
}
