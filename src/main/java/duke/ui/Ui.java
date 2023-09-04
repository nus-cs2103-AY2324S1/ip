package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.DukeList;
import duke.tasks.Task;

/**
 * The Ui class handles interactions with the user, including displaying messages and receiving input.
 */
public class Ui {
    private Scanner input;

    /**
     * Initializes a new Ui object with a Scanner for user input.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user's input command as a String.
     */
    public String readCommand() {
        return this.input.nextLine();
    }

    /**
     * Displays a line separator for a cleaner output.
     */
    public void showLine() {
        System.out.println("_______________________________________________________________");
    }

    /**
     * Displays the initial greeting message when the application starts.
     */
    public void showWelcome() {
        // Introduction
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Cleon\n" + " What can I do for you?\n");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showList(DukeList tasks) {
        this.showLine();
        System.out.println("Here are the tasks in your list:");
        int len = tasks.getSize();
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            Task currTask = tasks.getTask(i);
            System.out.println(num + ". " + currTask.toString());
        }
    }

    /**
     * Displays an error message when there is an issue loading past data.
     */
    public void showLoadingError() {
        System.out.println("Issues loading past data. Creating a new tasklist from scratch");
    }

    /**
     * Displays an error message with a custom error message.
     *
     * @param errMessage The error message to be displayed.
     */
    public void showError(String errMessage) {
        System.out.println(errMessage);
    }

    /**
     * Acknowledges the addition of a task to the task list.
     *
     * @param size The current size of the task list.
     * @param task The task that was added.
     */
    public void acknowledgeAdd(int size, Task task) {
        this.showLine();
        System.out.println("Added the following task to the list.");
        System.out.println(size + ". " + task.toString());
        System.out.println("You currently have " + size + " tasks in your list.");
        this.showLine();
    }

    /**
     * Acknowledges the deletion of a task from the task list.
     *
     * @param index The index of the task that was deleted.
     * @param task The task that was deleted.
     */
    public void acknowledgeDelete(int index, Task task) {
        this.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(index + ". " + task.toString());
        this.showLine();
    }

    /**
     * Acknowledges the result of a keyword-based task search.
     *
     * @param filteredTasks The list of tasks matching the search criteria.
     */
    public void acknowledgeFind(ArrayList<Task> filteredTasks) {
        this.showLine();
        if (filteredTasks.size() == 0) {
            System.out.println("No tasks with the given keyword can be found");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int len = filteredTasks.size();
            for (int i = 0; i < len; i++) {
                int num = i + 1;
                Task currTask = filteredTasks.get(i);
                System.out.println(num + ". " + currTask.toString());
            }
        }
    }

    /**
     * Acknowledges the marking of a task as done.
     *
     * @param index The index of the task that was marked as done.
     * @param task The task that was marked as done.
     */
    public void acknowledgeMark(int index, Task task) {
        this.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
        this.showLine();
    }

    /**
     * Acknowledges the marking of a task as not done yet.
     *
     * @param index The index of the task that was marked as not done yet.
     * @param task The task that was marked as not done yet.
     */
    public void acknowledgeUnmark(int index, Task task) {
        this.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
        this.showLine();
    }

    /**
     * Displays a farewell message when the application is exiting.
     */
    public void exit() {
        this.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }
}
