package duke;

import duke.task.Task;

/**
 * The Ui class handles user interface interactions and displays messages to the user.
 */
public class Ui {

    /**
     * Displays a greeting message when the chatbot starts.
     *
     * @return The greeting message.
     */
    public static String greet() {
        String message = printLine() + "\n Hello! I'm Doraemon\n" + " What can I do for you?\n" + printLine();
        return message;
    }

    /**
     * Displays a goodbye message when the chatbot exits.
     *
     * @return The goodbye message.
     */
    public static String goodbye() {
        String message = printLine() + "\n Bye. Hope to see you again soon!\n" + printLine();
        return message;
    }

    /**
     * Prints a horizontal line as a separator.
     *
     * @return A string representing a horizontal line separator.
     */
    public static String printLine() {
        return "---------------------------------------------";
    }

    /**
     * Generates a message to display all tasks in the provided TaskList.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     * @return A message containing all tasks in the TaskList.
     */
    public static String getAllTasksMessage(TaskList tasks) {
        StringBuilder result = new StringBuilder(" Here are the tasks in your list:\n");
        return result.append(tasks.toString()).toString();
    }

    /**
     * Generates a message for displaying a task.
     *
     * @param task The task to display.
     * @return The task message.
     */
    public static String getTaskMessage(Task task) {
        return "   " + task.toString() + "\n";
    }

    /**
     * Generates a message to confirm that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     * @return The confirmation message.
     */
    public static String getMarkMessage(Task task) {
        return " Nice! I've marked this task as done:\n" + getTaskMessage(task);
    }

    /**
     * Generates a message to confirm that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return The confirmation message.
     */
    public static String getUnmarkMessage(Task task) {
        return " OK, I've marked this task as not done yet:\n" + getTaskMessage(task);
    }

    /**
     * Generates a message to confirm that a task has been added.
     *
     * @param tasks The TaskList containing the tasks.
     * @param task  The task that was added.
     * @return The confirmation message.
     */
    public static String getAddTaskMessage(TaskList tasks, Task task) {
        return " Got it. I've added this task:\n" + getTaskMessage(task) + " Now you have " + tasks.getSize()
                + " tasks in the list.";
    }

    /**
     * Generates a message to confirm that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @return The confirmation message.
     */
    public static String getDeleteMessage(Task task) {
        return " Noted. I've removed this task:\n" + getTaskMessage(task);
    }

    /**
     * Generates a message to display matching tasks when searching for tasks.
     *
     * @param tasks The TaskList containing matching tasks.
     * @return The message with matching tasks.
     */
    public static String getFindMessage(TaskList tasks) {
        return " Here are the matching tasks in your list:\n" + tasks.toString();
    }

    /**
     * Generates a message for unknown commands.
     *
     * @return The message for unknown commands.
     */
    public static String getUnknownCommandMessage() {
        return " OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
