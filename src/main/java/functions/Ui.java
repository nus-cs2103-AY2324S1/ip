package functions;

import java.util.ArrayList;
import tasks.Task;

/**
 * Manages user interface interactions and displays messages to the user.
 */
public class Ui {

    /**
     * Constructs a Ui object with a new Scanner for reading user input.
     */
    public Ui(){
    }

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return "Hello! I'm CR7.CR7\n" + "What can I do for you?\n";
    }

    /**
     * Displays a message about adding a task and the current task count.
     *
     * @param t     The task that was added.
     * @param tasks The TaskList containing the tasks.
     */
    public String showTaskMsg(Task t, TaskList tasks) {
        return "Got it. I've added this task:\n"
                + "  " + t.toString() + "\n"
                + "Now you have " + tasks.numOfTasks() + " tasks in the list\n";
    }

    /**
     * Displays an exit message to the user.
     */
    public String showExitMsg() {
        return "Bye! Hope to see you again soon! SIUUUU\n";
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param tasks The ArrayList of tasks to be displayed.
     */
    public String showListMsg(ArrayList<Task> tasks) {
        String list = "Here are the tasks in your list:\n";
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task x = tasks.get(i - 1);
            list += i + "." + x.toString() + "\n";
        }
        return list;
    }

    /**
     * Displays matching tasks to the user after a search.
     *
     * @param tasks The ArrayList of matching tasks to be displayed.
     */
    public String showMatchesMsg(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks matching your description. " +
                    "Please try another search keyword.\n";
        } else {
            String list = "Here are the matching tasks in your list:";
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task x = tasks.get(i - 1);
                list += i + "." + x.toString() + "\n";
            }
            return list;
        }
    }

    /**
     * Displays a message about a task being deleted and the updated task count.
     *
     * @param k           The task that was deleted.
     * @param numOfTasks The updated number of tasks after deletion.
     */
    public String showDeleteMsg(Task k, int numOfTasks) {
        return "Noted. I've removed this task:\n"
                + "  " + k.toString() + "\n"
                + "Now you have " + numOfTasks + " tasks in the list.\n";
    }

    /**
     * Displays a message about a task being marked as done.
     *
     * @param k The task that was marked as done.
     */
    public String showMarkMsg(Task k) {
        return "Nice! I've marked this task as done:\n"
                + "  " + k.toString() + "\n";
    }

    /**
     * Displays a message about a task being marked as not done.
     *
     * @param k The task that was marked as not done.
     */
    public String showUnmarkMsg(Task k) {
        return "OK, I've marked this task as not done yet:\n"
                + "  " + k.toString() + "\n";
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMsg The error message to be displayed.
     */
    public String showErrorMsg(String errorMsg) {
        return "OOPS!!! I've encountered a problem here!"
                + errorMsg;
    }
}