package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles interactions with the user by displaying messages and receiving input.
 */
public class Ui {

    /** The scanner to read user input from the console. */
    Scanner scanner;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** A horizontal line used for formatting. */
    public static final String HORIZONTAL_LINE = "        ____________________________________________________________\n";
    /** An indentation string used for formatting. */
    public static final String INDENT = "        ";

    /**
     * Displays a welcome message when the Duke application starts.
     */
    public void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE
                + INDENT + "Hello! I'm Glenda!\n"
                + INDENT + "What can I do for you?\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Displays a goodbye message when the Duke application ends.
     */
    public void showGoodbyeMessage() {
        System.out.println(HORIZONTAL_LINE
                + INDENT + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Displays the task added and the current total number of tasks in the list.
     * @param task The task that was added.
     * @param numberOfTasks The number of tasks in the list currently.
     */
    public void showAddedTask(Task task, int numberOfTasks) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Got it. I've added this task to the list:");
        System.out.println("          " + task.toString());
        System.out.println(INDENT + "Now you have " + numberOfTasks + " task(s) in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays all the tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.print(HORIZONTAL_LINE);

        if (tasks.isEmpty()) {
            // Case where there is no tasks to be displayed
            System.out.println(INDENT + "No tasks added. ");
        } else {
            System.out.println(INDENT + "Here are the task(s) in your list:");

            for (Task task : tasks) {
                System.out.println(INDENT + (tasks.indexOf(task) + 1) + ". " + task);
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating the task was marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Great! I've completed this task!");
        task.markAsDone();
        System.out.println(INDENT + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating the task was marked as undone.
     *
     * @param task The task that was marked undone.
     */
    public void showTaskMarkedAsUndone(Task task) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Okay, I have not yet completed this task:");
        task.markAsUndone();
        System.out.println(INDENT + task);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating the tasks that was deleted, and the number of tasks left in the task list.
     *
     * @param task The task that was deleted.
     * @param numberOfTasks The number of tasks left in the task list.
     */
    public void showDeletedTask(Task task, int numberOfTasks) {
        System.out.print(HORIZONTAL_LINE);
        System.out.println(INDENT + "Okay, I've removed this task:");
        System.out.println("          " + task.toString());
        System.out.println(INDENT + "Now you have " + numberOfTasks + " task(s) in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message shown to the user.
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(HORIZONTAL_LINE
                + INDENT + errorMessage + "\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Displays the list of tasks matching the keyword search.
     *
     * @param tasks Tasks associated to the keyword search.
     */
    public void showMatchingTasksToKeyword(ArrayList<Task> tasks) {
        System.out.print(HORIZONTAL_LINE);

        if (tasks.isEmpty()) {
            // Case where there is no matching tasks to be displayed
            System.out.println(INDENT + "No matching tasks in the list. ");
        } else {
            System.out.println(INDENT + "Here are the matching task(s) in your list:");

            for (Task task: tasks) {
                System.out.println(INDENT + (tasks.indexOf(task) + 1) + ". " + task.toString());
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }
}

