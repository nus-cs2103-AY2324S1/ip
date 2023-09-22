package com.nyanbot.dukeuiclasses;

import java.util.ArrayList;

import com.nyanbot.duketasks.Task;

/**
 * Encapsulates a class which will print out normal UI operations.
 *
 * @author Tan Kerway
 *
 */
public class DukeUi {

    /**
     * Returns the greeting string for the user. Lists out any tasks
     * saved from previous sessions
     *
     * @author Tan Kerway
     */
    public String getGreeting(ArrayList<Task> tasks) {
        return "Hello! I'm nyancatbot!\nWhat can I do for nyan?\n"
                // list all the tasks
                + listAllTasks(tasks);
    }

    /**
     * Prints the current list of tasks.
     *
     * @author Tan Kerway
     * @param tasks the list of tasks to print out.
     */
    public String listAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "";
        }
        StringBuilder tasksString = new StringBuilder();
        tasksString.append("Here are the tasks in your list :3\n");
        tasksString.append("================================\n");
        for (int i = 0; i < tasks.size(); i++) {
            tasksString.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        tasksString.append("================================\n");
        return tasksString.toString();
    }

    /**
     * Echos the task that was added to the task list to
     * the console.
     *
     * @author Tan Kerway
     * @param task the task to be echoed to the console
     * @return the String that represents the chatbot's response
     */
    public String echoTaskAdded(Task task, int tasksCount) {
        if (task == null) {
            return "";
        }
        return "Got it. I've added this task:\n    "
                + task + "\nNyan you have " + tasksCount + " tasks in the list.\n";
    }

    /**
     * Echos that the task has been marked.
     *
     * @author Tan Kerway
     * @param currentTask the marked task to be echoed
     * @return the String that lets the user know that the given task
     *          has been marked as done
     */
    public String echoTaskMarked(Task currentTask) {
        assert currentTask != null : "given task should be non null";
        return "Nice! I've marked this task as nyan:\n"
                + "    "
                + currentTask;
    }

    /**
     * Echos that the task has been deleted.
     *
     * @author Tan Kerway
     * @param removedTask the index of the deleted task
     * @param tasksCount the number of tasks currently
     * @return the String that represents the chatbot's response
     */
    public String echoTaskDeleted(Task removedTask, int tasksCount) {
        assert removedTask != null : "given task should be non null";
        return "Noted. I've removed this task:\n"
                + "    " + removedTask + "\n"
                + "Now you have " + tasksCount + " tasks in the list.";
    }

    /**
     * Echos that the task has been marked.
     *
     * @author Tan Kerway
     * @param currentTask the marked task to be echoed
     * @return the String that represents the chatbot's response
     */
    public String echoTaskUnmarked(Task currentTask) {
        assert currentTask != null : "given task should be non null";
        return "OK, I've marked this task as not nyan yet:\n"
                + "    " + currentTask;
    }
}
