package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The Ui class provides user interface functionality for interacting with the Duke application.
 * It displays messages to the user, such as welcome messages, task lists, and error messages.
 */
public class Ui {

    /**
     * Displays a welcome message to the user.
     */

    String line = "____________________________________________________________";


    public void showWelcomeMessage() {
        System.out.println(line);
        System.out.println(" Hello! I'm Axela");
        System.out.println(" What can I do for you?");
        System.out.println(line);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     * Displays an error message when tasks cannot be loaded from a file.
     */
    public void showLoadingError() {
        System.out.println(line);
        System.out.println("Error loading tasks from file.");
        System.out.println(line);
    }


    /**
     * Displays a list of tasks to the user.
     *
     * @param taskList The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println(line);

        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.get(i));
        }
        System.out.println(line);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task       The task that has been added.
     * @param totalTasks The total number of tasks after adding.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(line);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.println(line);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println(line);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println(line);
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println(line);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task       The task that has been deleted.
     * @param totalTasks The total number of tasks after deletion.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println(line);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(line);
        System.out.println(" " + message);
        System.out.println(line);
    }
}
