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
     * Displays a message to the user.
     *
     * @param s The message to be displayed.
     */
    public String showMessage(String s) {
        return s;
    }

    /**
     * Displays a horizontal line separator.
     */
    public String showLine() {
        return "____________________________________________________________\n";
    }

    /**
     * Displays a message indicating the addition of a task.
     *
     * @param t The task that was added.
     * @param lst The task list containing the tasks.
     */
    public String addTask(Task t, TaskList lst) {
        return "Got it. I've added this task:\n"
                + "  " + t + "\n"
                + "Now you have " + lst.size() + " tasks in the list.\n";
    }

    /**
     * Displays a list of tasks.
     *
     * @param lst The task list containing the tasks to be displayed.
     */
    public String list(TaskList lst) {
        StringBuilder res = new StringBuilder("Here are the tasks in your list:\n");
        for (Task t : lst) {
            res.append(lst.indexOf(t) + 1).append(". ").append(t).append("\n");
        }
        return res.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param t The task that was marked as done.
     */
    public String mark(Task t) {
        return "Nice! I've marked this task as done:\n"
                + "  " + t + "\n";
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param t The task that was unmarked.
     */
    public String unmark(Task t) {
        return "OK, I've marked this task as not done yet:" + "\n"
                + "  " + t + "\n";
    }

    /**
     * Displays a message indicating the deletion of a task.
     *
     * @param t The task that was deleted.
     */
    public String delete(Task t) {
        return "Noted. I've removed this task:\n" + t + "\n";
    }

    /**
     * Displays a list of tasks that match the search criteria.
     * If the provided list is empty, a message indicating no matching tasks is displayed.
     *
     * @param lst The list of tasks that match the search criteria.
     */
    public String showMatch(ArrayList<Task> lst) {
        if (lst.isEmpty()) {
            return "There is no task matching your request";
        } else {
            StringBuilder res = new StringBuilder("Here are the matching tasks in your list:\n");
            for (Task t : lst) {
                res.append(lst.indexOf(t) + 1).append(". ").append(t).append("\n");
            }
            return res.toString();
        }
    }
}
