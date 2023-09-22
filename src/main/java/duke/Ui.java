package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the user interface for Duke.
 */
public class Ui {

    /**
     * Constructs an Ui object and initializes the scanner to read user input.
     */
    public Ui() {}

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm YJ's Chat bot\nWhat can I do for you?";
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showByeMessage() {
        return "Bye! Hope to see you soon!";
    }

    /**
     * Displays an error message when loading tasks from a save file fails.
     *
     * @param msg The error message explaining the failure.
     */
    public void showLoadingError(String msg) {
        System.out.println("Failed to load existing save file due to: " + msg);
    }

    /**
     * Displays an error message when adding a task fails.
     *
     * @param msg The error message explaining the failure.
     */
    public String showAddTaskError(String msg) {
        return "Failed to add task because: " + msg;
    }


    /**
     * Displays the number of tasks in the list.
     *
     * @param numTasks The number of tasks in the list.
     */
    private String showNumberOfTasks(int numTasks) {
        return "There are now " + numTasks + " tasks in the list";
    }

    /**
     * Displays a message confirming the addition of a task and the updated task count.
     *
     * @param task The task that was added.
     * @param numTasks The updated number of tasks in the list.
     */
    public String showAddTaskMessage(Task task, int numTasks) {
        return "Got it! I've added this task:\n" + task + "\n" + showNumberOfTasks(numTasks);
    }

    /**
     * Displays a message confirming the deletion of a task and the updated task count.
     *
     * @param task The task that was deleted.
     * @param numTasks The updated number of tasks in the list.
     */
    public String showDeleteTaskMessage(Task task, int numTasks) {
        return "Got it! I've deleted this task:\n" + task + "\n" + showNumberOfTasks(numTasks);
    }

    /**
     * Displays an error message for when a user provides an invalid task index
     */
    public String showInvalidIndexError() {
        return "Invalid index provided! Make sure it is a number and within the range of number of tasks!"
    }

    /**
     * Displays a message indicating a task has been marked as done, along with the task that was marked
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    /**
     * Displays a message indicating a task has been marked as not being done, along with the task that was marked
     *
     * @param task The task that was marked as not being done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println(task);
        showLine();
    }

    /**
     * Displays an error message for an invalid command.
     */
    public String showInvalidCommandError() {
        return "Invalid command!";
    }

    /**
     * Displays an error message when saving tasks to a save file fails.
     *
     * @param e The exception thrown when trying to save the file.
     */
    public void showSaveTasksError(IOException e) {
        System.out.println("Failed to save tasks to drive: " + e.getMessage());
        showLine();
    }

    /**
     * Displays the list of tasks from the given task list.
     *
     * @param taskList The task list to be displayed.
     */
    public String showTaskList(TaskList taskList) {
        return "Here's the list of tasks you have!\n" + taskList.toString();
    }

    public String showFindResults(TaskList taskList) {
        if (taskList.getTasks().isEmpty()) {
            return "No tasks with that keyword exists!";
        } else {
            return"Here are the matching tasks in your list\n"+ taskList;
        }
    }

}
