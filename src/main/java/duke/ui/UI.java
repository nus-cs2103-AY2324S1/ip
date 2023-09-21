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
    /**
     * Displays a message indicating the addition of a task.
     *
     * @param t The task that was added.
     * @param lst The task list containing the tasks.
     */
    public String addTask(Task t, TaskList lst) {
        return "Okay you have one more thing to do:\n"
                + "  " + t + "\n"
                + "Now you have " + lst.size() + " tasks haven't finished.\n";
    }

    /**
     * Displays a list of tasks.
     *
     * @param lst The task list containing the tasks to be displayed.
     */
    public String list(TaskList lst) {
        StringBuilder res = new StringBuilder("These are the tasks you haven't finished:\n");
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
        return "It seems like you finished one task:\n"
                + "  " + t + "\n";
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param t The task that was unmarked.
     */
    public String unmark(Task t) {
        return "So you didn't finish it:" + "\n"
                + "  " + t + "\n";
    }

    /**
     * Displays a message indicating the deletion of a task.
     *
     * @param t The task that was deleted.
     */
    public String delete(Task t) {
        return "Okay, you don't need to worry about it now:\n" + t + "\n";
    }

    /**
     * Displays a message indicating the snoozing of a task.
     *
     * @param t The task that was snoozed.
     */
    public String snooze(Task t) {
        return "OKay you can put this matter aside for now:\n" + t + "\n";
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
            StringBuilder res = new StringBuilder("Here are the related tasks you need to do:\n");
            for (Task t : lst) {
                res.append(lst.indexOf(t) + 1).append(". ").append(t).append("\n");
            }
            return res.toString();
        }
    }
}
