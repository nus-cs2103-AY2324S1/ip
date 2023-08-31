package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the user interface for Duke.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs an Ui object and initializes the scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Closes the scanner used for reading user input.
     * Only to be used when shutting down Duke.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays a line separator in the console.
     */
    public static void showLine() {
        System.out.println("-------------------------------");
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm YJ's Chatbot");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showByeMessage() {
        System.out.println("Bye! Hope to see you soon!");
        showLine();
    }

    /**
     * Displays an error message when loading tasks from a save file fails.
     *
     * @param msg The error message explaining the failure.
     */
    public void showLoadingError(String msg) {
        System.out.println("Failed to load existing save file due to: " + msg);
        showLine();
    }

    /**
     * Displays an error message when adding a task fails.
     *
     * @param msg The error message explaining the failure.
     */
    public void showAddTaskError(String msg) {
        System.out.println("Failed to add task because: " + msg);
        showLine();
    }


    /**
     * Displays the number of tasks in the list.
     *
     * @param numTasks The number of tasks in the list.
     */
    private void showNumberOfTasks(int numTasks) {
        System.out.println("There are now " + numTasks + " tasks in the list");
        showLine();
    }

    /**
     * Displays a message confirming the addition of a task and the updated task count.
     *
     * @param task The task that was added.
     * @param numTasks The updated number of tasks in the list.
     */
    public void showAddTaskMessage(Task task, int numTasks) {
        System.out.println("Got it! I've added this task:");
        System.out.println(task);
        showNumberOfTasks(numTasks);
    }

    /**
     * Displays a message confirming the deletion of a task and the updated task count.
     *
     * @param task The task that was deleted.
     * @param numTasks The updated number of tasks in the list.
     */
    public void showDeleteTaskMessage(Task task, int numTasks) {
        System.out.println("Got it! I've deleted this task:");
        System.out.println(task);
        showNumberOfTasks(numTasks);
    }

    /**
     * Displays an error message for when a user provides an invalid task index
     */
    public void showInvalidIndexError() {
        System.out.println("Invalid index provided! Make sure it is a number and within the range of number of tasks!");
        showLine();
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
    public void showInvalidCommandError() {
        System.out.println("Invalid command!");
        showLine();
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
    public void showTaskList(TaskList taskList) {
        showLine();
        System.out.println(taskList.toString());
        showLine();
    }

    public void showFindResults(TaskList taskList) {
        showLine();
        if (taskList.getTasks().isEmpty()) {
            System.out.println("No tasks with that keyword exists!");
        } else {
            System.out.println("Here are the matching tasks in your list");
            System.out.println(taskList);
        }
        showLine();
    }

    public String readCmd() {
        // Read user input
        return scanner.nextLine();
    }

}
