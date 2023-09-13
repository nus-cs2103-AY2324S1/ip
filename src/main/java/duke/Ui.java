package duke;

import java.util.ArrayList;

/**
 * The Ui class is responsible for handling user interaction and displaying messages.
 */
public class Ui {

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays a goodbye message when the application exits.
     *
     * @return A goodbye message.
     */
    public String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows the added task and the number of tasks remaining.
     *
     * @param task The newly added task.
     * @param taskListSize The size of the task list after the addition of the new task.
     * @return A string containing the newly added task and the number of tasks in the list.
     */
    public String showAddTask(Task task, int taskListSize) {
        return ("Got it. I've added this task:\n"
                + task + "\n" + "Now you have " + taskListSize + " task(s) in the list.");
    }

    /**
     * Shows the removed task and the number of tasks remaining.
     *
     * @param task The removed task.
     * @param taskListSize The size of the task list after the removal of the deleted task.
     * @return A string containing the newly deleted task and the number of tasks in the list.
     */
    public String showDeletedTask(Task task, int taskListSize) {
        return ("Noted. I've removed this task:\n"
                + task + "\n" + "Now you have " + taskListSize + " task(s) in the list.");
    }

    /**
     * Displays a confirmation message stating that task is mark done.
     *
     * @param task The task marked as done.
     * @return A string containing the task marked as done.
     */
    public String showDone(Task task) {
        return ("Nice! I've marked this task as done:\n"
                + task + "\n");
    }

    /**
     * Displays a confirmation message stating that task is mark undone.
     *
     * @param task The task marked as undone.
     * @return A string containing the task marked as not done.
     */
    public String showUndone(Task task) {
        return ("OK, I've marked this task as not done yet:\n"
                + task + "\n");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The TaskList containing the tasks to display.
     * @return A string containing the complete task list.
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return ("Wow! You have no tasks in your task list!");
        }
        return tasks.toString();
    }

    /**
     * Displays the list of matching tasks found using the keyword to the user.
     *
     * @param matchingTasks The TaskList containing the tasks to display.
     * @return A string containing all matching tasks.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return ("No matching tasks found.");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                stringBuilder.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }

            return stringBuilder.toString();
        }
    }

    /**
     * Displays a message stating that the task list is in the archive.
     *
     * @return A string stating the location of the archived file.
     */
    public String showArchiveMessage() {
        return ("Your task list has been successfully archived in ./data/archive.txt.");
    }
}
