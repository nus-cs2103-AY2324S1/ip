package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasklist.Task;
import duke.tasklist.TaskList;

/**
 * Handles user interface interactions in the Duke application.
 * This class manages user input and output, providing methods for displaying messages and tasks.
 */
public class UI {
    private final Scanner sc;

    /**
     * Constructs a UI object with a scanner to read user input.
     */
    public UI() {
        sc = new Scanner(System.in);
    }
    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hey summoner! I'm\n"
                + "  ---  " + "  ----- " + "  ----- " + "   ---  \n"
                + " / _ \\ " + " |     |" + " |     |" + "  / _ \\ \n"
                + "| | | |" + "  -   - " + "  -   - " + " | | | |\n"
                + "| |_| |" + "   | |  " + "   | |  " + " | |_| |\n"
                + " \\ _ / " + "   | |  " + "   | |  " + "  \\ _ /\n "
                + " ---  " + "   |_|  " + "   |_|  " + "   ---  \n"
                + "What do you want me to do?\n");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user-entered command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param s The message to be displayed.
     */
    public void showMessage(String s) {
        System.out.println(s);
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays a message indicating the addition of a task.
     *
     * @param t The task that was added.
     * @param lst The task list containing the tasks.
     */
    public void addTask(Task t, TaskList lst) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
    }

    /**
     * Displays a list of tasks.
     *
     * @param lst The task list containing the tasks to be displayed.
     */
    public void list(TaskList lst) {
        System.out.println("Here are the tasks in your list:");
        lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + ". " + x));
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param t The task that was marked as done.
     */
    public void mark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t + "\n");
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param t The task that was marked as not done.
     */
    public void unmark(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t + "\n");
    }

    /**
     * Displays a message indicating the deletion of a task.
     *
     * @param t The task that was deleted.
     */
    public void delete(Task t) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
    }

    /**
     * Displays a list of tasks that match the search criteria.
     * If the provided list is empty, a message indicating no matching tasks is displayed.
     *
     * @param lst The list of tasks that match the search criteria.
     */
    public void showMatch(ArrayList<Task> lst) {
        if (lst.isEmpty()) {
            System.out.println("There is no task matching your request");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + ". " + x));
        }
    }
}
