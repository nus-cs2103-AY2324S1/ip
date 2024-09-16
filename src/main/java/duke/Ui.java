package duke;

import java.util.List;

import duke.tasks.Task;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Returns the welcome message.
     *
     * @return Welcome message as a string.
     */
    public static String getWelcomeMessage() {
        return "Sup bro! I'm Brobot\n"
                + "What can I do for you?";
    }

    /**
     * Returns the farewell message.
     *
     * @return Exit message as a string.
     */
    public static String getExitMessage() {
        return "Bye. Hope to see you again soon bro!";
    }

    /**
     * Returns the message when a task is added.
     *
     * @param task The task that is added.
     * @param list The list of tasks.
     * @return Message when a task is added.
     */
    public static String getAddTaskMessage(Task task, List<Task> list) {
        return "Got it bro! I've added this task:\n" + task + "\n"
                + "Now you have " + list.size() + " tasks in the list";
    }

    /**
     * Returns the message when a task is deleted.
     *
     * @param task The task that is deleted.
     * @param list The list of tasks.
     * @return Message when a task is deleted.
     */
    public static String getDeleteTaskMessage(Task task, List<Task> list) {
        return "Noted! I've deleted this task from the list:\n"
                + task + "\nNow you have " + list.size() + " tasks in the list";
    }

    /**
     * Returns the message when a task is marked as done.
     *
     * @param task The task that is marked as done.
     * @return Message when a task is marked as done.
     */
    public static String getMarkedTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + "" + task;
    }

    /**
     * Returns the message when a task is unmarked.
     *
     * @param task The task that is unmarked.
     * @return Message when a task is unmarked.
     */
    public static String getUnmarkTaskMessage(Task task) {
        return "Nice! I've marked this task as not done yet:\n" + "" + task;
    }

    /**
     * Returns all tasks in the list in a String format.
     *
     * @return All the tasks in the list as a string.
     */
    public static String getListAsString(List<Task> list) {
        StringBuilder s = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            s.append("" + (i + 1) + ". " + list.get(i) + "\n");
        }
        return s.toString();
    }

    /**
     * Returns all the specified type of tasks in the current list in sorted order.
     *
     * @param sortedTasks List of sorted tasks.
     * @param taskType    Type of task of sorted tasks.
     * @return List of sorted tasks as a string.
     */
    public static String getSortedTasks(List<? extends Task> sortedTasks, String taskType) {
        StringBuilder s = new StringBuilder("Here are the sorted " + taskType + "s in your list:\n");
        for (int i = 0; i < sortedTasks.size(); i++) {
            s.append("" + (i + 1) + ". " + sortedTasks.get(i) + "\n");
        }
        return s.toString();
    }


}
