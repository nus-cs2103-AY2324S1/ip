package ipbot.util;

import ipbot.model.Task;

/**
 * A class to handle all interactions with the user.
 */
public class Ui {
    /**
     * Returns a task as a String in list format.
     *
     * @param task The task to be printed.
     * @param number The number to be given to the task in the list.
     */
    public static String taskListFormatString(Task task, int number) {
        return String.format("%d. %s\n", number, task.toString());
    }

    /**
     * Returns a notification that a task has been added.
     *
     * @param task The task that was added.
     * @param taskTypeStr The type of task that was added.
     */
    public static String addedItemString(Task task, String taskTypeStr) {
        return "Added " + taskTypeStr + " item: " + task.toString();
    }

    /**
     * Returns a notification that a task has been deleted.
     *
     * @param task The task that was deleted.
     */
    public static String deletedItemString(Task task) {
        return "Deleted item: " + task.toString();
    }

    /**
     * Returns a notification that a task has been marked or unmarked.
     *
     * @param task The task that was marked or unmarked.
     * @param markAsDone Whether we are marking or unmarking the task.
     */
    public static String markTaskString(Task task, boolean markAsDone) {
        String done = markAsDone ? "done" : "undone";
        return "Marking task as " + done + ": " + task.toString();
    }

    /**
     * Returns a notification that we are marking or unmarking a task that has already been marked or unmarked.
     *
     * @param task The task that was marked or unmarked.
     * @param markAsDone Whether we are marking or unmarking the task.
     */
    public static String alreadyMarkedTaskString(Task task, boolean markAsDone) {
        String done = markAsDone ? "done" : "undone";
        return "Task was already marked as " + done + ": " + task.toString();
    }
}
