package functions;

import java.util.ArrayList;
import java.util.Scanner;
import tasks.Task;

/**
 * Manages user interface interactions and displays messages to the user.
 */
public class Ui {

    /**
     * Scanner object for reading user input.
     */
    protected Scanner scanner;

    /**
     * Constructs a Ui object with a new Scanner for reading user input.
     */
    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user-entered command as a string.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays a line of dashes for separation.
     */
    public void showLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println(); // Move to the next line
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm CR7\n" + "What can I do for you?\n");
    }

    /**
     * Displays a message about adding a task and the current task count.
     *
     * @param t     The task that was added.
     * @param tasks The TaskList containing the tasks.
     */
    public void showTaskMsg(Task t, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + tasks.numOfTasks() + " tasks in the list\n");
    }

    /**
     * Displays an exit message to the user.
     */
    public void showExitMsg() {
        System.out.println("Bye! Hope to see you again soon! SIUUUU\n");
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The ArrayList of tasks to be displayed.
     */
    public void showListMsg(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task x = tasks.get(i - 1);
            System.out.println(i + "." + x.toString());
        }
        System.out.println();
    }

    /**
     * Displays matching tasks to the user after a search.
     *
     * @param tasks The ArrayList of matching tasks to be displayed.
     */
    public void showMatchesMsg(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks matching your description. " +
                    "Please try another search keyword.\n");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task x = tasks.get(i - 1);
                System.out.println(i + "." + x.toString());
            }
            System.out.println();
        }
    }

    /**
     * Displays a message about a task being deleted and the updated task count.
     *
     * @param k           The task that was deleted.
     * @param numOfTasks The updated number of tasks after deletion.
     */
    public void showDeleteMsg(Task k, int numOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + k.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.\n");
    }

    /**
     * Displays a message about a task being marked as done.
     *
     * @param k The task that was marked as done.
     */
    public void showMarkMsg(Task k) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + k.toString() + "\n");
    }

    /**
     * Displays a message about a task being marked as not done.
     *
     * @param k The task that was marked as not done.
     */
    public void showUnmarkMsg(Task k) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + k.toString() + "\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMsg The error message to be displayed.
     */
    public void showErrorMsg(String errorMsg) {
        System.out.println("OOPS!!! I've encountered a problem here!");
        System.out.println(errorMsg);
    }
}