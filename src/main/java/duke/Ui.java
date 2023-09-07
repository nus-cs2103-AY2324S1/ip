package duke;

import duke.task.Task;

/**
 * This class handles the UI of the application.
 */
public class Ui {
    /**
     * Returns a string of all the duke.task.Task instances in the list with their respective indices.
     *
     * @param list The list containing the tasks.
     * @return The message string.
     */
    public String showList(TaskList list) {
        return Messages.MESSAGE_LIST + list.printTasks();
    }

    /**
     * Returns a string of the new task added along with its associated message and
     * the new count of tasks.
     *
     * @param task       The new task added.
     * @param totalTasks The total count of tasks in the list.
     * @return The message string.
     */
    public String showTaskAdded(Task task, int totalTasks) {
        return String.format("%s  %s\n", Messages.MESSAGE_NEW_TASK_ADDED, task)
                + String.format(Messages.MESSAGE_TOTAL_TASK_COUNT, totalTasks);
    }

    /**
     * Returns a string of the task deleted along with its associated message and
     * the new count of tasks.
     *
     * @param task       The deleted task.
     * @param totalTasks The total count of tasks in the list.
     * @return The message string.
     */
    public String showTaskDeleted(Task task, int totalTasks) {
        return String.format("%s  %s\n", Messages.MESSAGE_TASK_DELETED, task)
                + String.format(Messages.MESSAGE_TOTAL_TASK_COUNT, totalTasks);
    }

    /**
     * Returns a string of the task marked as done along with its associated message.
     *
     * @param task The done task.
     * @return The message string.
     */
    public String showTaskDone(Task task) {
        return Messages.MESSAGE_MARK_DONE + "  " + task + '\n';
    }

    /**
     * Returns a string of the task marked as undone along with its associated message.
     *
     * @param task The undone task.
     * @return The message string.
     */
    public String showTaskUndone(Task task) {
        return Messages.MESSAGE_MARK_UNDONE + "  " + task + '\n';
    }

    /**
     * Returns a String of the greeting message.
     *
     * @return The message string.
     */
    public static String showGreeting() {
        return Messages.MESSAGE_GREETING;
    }

    /**
     * Returns a String of the exit message.
     *
     * @return The message string.
     */
    public static String showExitMessage() {
        return Messages.MESSAGE_EXIT;
    }
}
