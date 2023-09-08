package chatbuddy.ui;

import java.util.ArrayList;

import chatbuddy.task.Task;

/**
 * The Ui class handles interactions with the user.
 * It reads inputs from the users and output messages to the user.
 */
public class Ui {

    /**
     * Returns the welcome message.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Chat Buddy!\nWhat can I do for you?";
    }

    /**
     * Returns the exit message.
     *
     * @return The exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!\nPlease close the interface to exit!";
    }

    /**
     * Returns a string representing a list of tasks.
     *
     * @param taskStrings The list of tasks represented by Strings.
     * @param message The message to print before the list.
     * @return A string representing a list of tasks.
     */
    public String showTaskList(ArrayList<String> taskStrings, String message) {
        StringBuilder sb = new StringBuilder(message);
        sb.append(System.lineSeparator());
        for (int i = 0; i < taskStrings.size(); i++) {
            String taskString = taskStrings.get(i);
            sb.append(String.format("%1s. %2s\n", i + 1, taskString));
        }
        return sb.toString();
    }

    /**
     * Returns a confirmation message about the addition of a task.
     *
     * @param task The task that was added.
     * @param totalNumOfTasks The number of tasks after the task was added.
     * @return A confirmation message about the addition of a task.
     */
    public String showTaskAddition(Task task, int totalNumOfTasks) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + totalNumOfTasks + " tasks in the list.";
    }

    /**
     * Returns a confirmation message about the deletion of a task.
     *
     * @param task The task that was deleted.
     * @param totalNumOfTasks The number of tasks after the task was deleted.
     * @return A confirmation message about the deletion of a task.
     */
    public String showTaskDeletion(Task task, int totalNumOfTasks) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + totalNumOfTasks + " tasks in the list.";
    }

    /**
     * Returns a confirmation message about a task being marked as done.
     *
     * @param task The task that was marked as done.
     * @return A confirmation message about a task being marked as done.
     */
    public String showMarkTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a confirmation message about a task being marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return A confirmation message about a task being marked as not done.
     */
    public String showUnmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
