package sally;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Displays an error message indicating a loading error.
     */
    public void showLoadingError() {
        System.out.println("OOPS! Something went wrong while loading your tasks.");
    }

    /**
     * Displays a welcome message to greet the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! It's Sally here!");
        System.out.println("How can I help you today?");
    }

    /**
     * Displays a goodbye message to bid farewell to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye! See you again next time.");
    }

    /**
     * Displays a message about a deleted task and the updated task count.
     *
     * @param task The task that was deleted.
     * @param size The new size of the task list.
     */
    public void showDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     */
    public void showTasks(TaskList tasks) {
        System.out.println("My list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * Displays a message about a marked task.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task);
    }

    /**
     * Displays a message about an unmarked task.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }

    /**
     * Displays a message about an added task and the updated task count.
     *
     * @param task The task that was added.
     * @param size The new size of the task list.
     */
    public void showAddedTask(Task task, int size) {
        System.out.println("Added to My List: ");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays the list of matching tasks to the user.
     *
     * @param matchingTasks A TaskList containing the tasks that match the search criteria.
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.getSize(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.getTask(i));
        }
    }
  
    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
