package dre.ui;

import java.util.Scanner;
import dre.task.TaskList;
import dre.exception.DreException;
import dre.task.Task;

/**
 * Represents the User Interface responsible for interactions between the user and the application.
 */
public class Ui {

    /**
     * Displays an error message indicating issues with loading tasks from storage.
     */
    public void showLoadingError() {
        System.out.println("Error reading tasks from file.");
    }

    /**
     * Displays a specified error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a line separator in the user interface.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the welcome message to the user upon starting the application.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Dre");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message to the user upon exiting the application.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays the tasks present in the user's task list.
     *
     * @param tasks The task list to be displayed.
     * @throws DreException If there is an error while processing the task list.
     */
    public void showTasks(TaskList tasks) throws DreException {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println((i) + ". " + tasks.getTask(i));
        }
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("Ok! I've marked this task as undone:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     */
    public void showDeletedTask(Task task) {
        System.out.println("Task deleted:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks in the task list after addition.
     */
    public void showAddedTask(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays a message indicating the tasks that match the search.
     *
     * @param tasks The tasks that match the search description.
     * @throws DreException If there is an error while processing the task list.
     */
    public void showFoundTasks(TaskList tasks) throws DreException {
        if (tasks.size() < 1) {
            System.out.println("Sorry, no tasks match that description.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println((i) + "." + tasks.getTask(i));
        }
    }

    /**
     * Reads the user's command input.
     *
     * @return The user's input as a string.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
