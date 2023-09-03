package arona.ui;

import java.util.ArrayList;

import arona.task.Task;
import arona.task.TaskList;
import java.util.ArrayList;

/**
 * The UI class handles user interface-related functionality.
 */
public class Ui {

    /**
     * Displays a welcome message when the application starts.
     */
    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm Arona, your Virtual Assistant.");
        System.out.println("What can I do for you today?\n");
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public static void showGoodbyeMessage() {
        System.out.println("Goodbye. See you soon!");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public static void showTaskList(TaskList tasks) {
        if (tasks.getTasks().isEmpty()) {
            System.out.println("Great job! No tasks right now, enjoy your day!\n");
        } else {
            System.out.println("Hello! Here is your list of tasks:");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                System.out.println((i + 1) + ". " + tasks.getTasks().get(i));
            }
            System.out.println("");
        }
    }

    /**
     * Displays a message confirming the addition of a task.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks after adding.
     */
    public static void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it! I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + (totalTasks == 1 ? " task" : " tasks") + " in the list.\n");
    }

    /**
     * Displays a message confirming the removal of a task.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks after removal.
     */
    public static void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("Sure thing! I've removed this task:");
        System.out.println("  " + task + "\n");
        System.out.println("Now you have " + totalTasks + (totalTasks == 1 ? " task" : " tasks") + " in the list.\n");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static void showTaskMarkedAsDone(Task task) {
        System.out.println("Awesome! I've marked this task as done:");
        System.out.println("  " + task + "\n");
    }

    /**
     * Displays a message confirming that a task has been marked as not done yet.
     *
     * @param task The task that was marked as not done yet.
     */
    public static void showTaskUnmarked(Task task) {
        System.out.println("Sure thing! I've marked this task as not done yet:");
        System.out.println("  " + task + "\n");
    }

    /**
     * Displays an error message when a specified task does not exist.
     *
     * @param taskIndex The index of the task that does not exist.
     */
    public static void showTaskDoesNotExist(int taskIndex) {
        System.out.println("Sorry... I can't find the task. Please input the task number correctly!\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param e The exception that occurred.
     */
    public static void showErrorMessage(Exception e) {
        System.out.println(e.getMessage() + "\n");
    }

    /**
     * Displays a message indicating that the user input is invalid.
     */
    public static void showInvalidArgumentMessage() {
        System.out.println("Oops! I'm not quite sure what that means...\n");
    }

    /**
     * Displays the search results to the user.
     *
     * @param result The TaskList containing the matching tasks.
     */
    public static void showSearchResult(TaskList result) {
        if (result.getTasks().isEmpty()) {
            System.out.println("Ehhh... There are no matching tasks.\n");
        } else {
            System.out.println("Yay! Here are your matching tasks:");
            ArrayList<Task> tasks = result.getTasks();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            System.out.println();
        }
    }
}
