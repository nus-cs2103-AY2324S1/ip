package duke;

import java.util.ArrayList;

/**
 * Class that deals with interactions with the user.
 */
public class Ui {
    /**
     * Shows a loading error if failing to initialise starting classes.
     */
    public static void showLoadingError() {
        System.out.println("Unable to initialise duke.Duke.");
    }

    /**
     * Shows welcome message.
     */
    public static String showWelcome() {
        return ("Hello I'm iP");
    }

    /**
     * Shows a list of tasks.
     * @param tasks Tasks to show.
     */
    public static String listTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder();
        response.append("List of tasks:").append("\n");
        for (Task task : tasks) {
            response.append(task.toString()).append("\n");
        }
        response.append("You have ").append(tasks.size()).append(" tasks in the list.");
        return response.toString();
    }

    /**
     * Returns the message to be sent to the user after a successful delete task operation.
     * @param taskDeleted The task that was deleted.
     */
    public static String deleteTask(Task taskDeleted) {
        return "Noted. I've removed this task:" + "\n"
                + taskDeleted.toString() + "\n";
    }

    /**
     * Returns the message to be sent to the user after a successful mark task operation.
     * @param taskCompleted The task that was marked.
     */
    public static String markTask(Task taskCompleted) {
        return "Nice! I've marked this task as done:" + "\n"
                + taskCompleted.toString() + "\n";
    }

    /**
     * Shows a list of given tasks to the user.
     * @param tasks Tasks to be shown to the user.
     */
    public static String foundTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:").append("\n");
        for (Task task : tasks) {
            response.append(task.toString());
        }
        return response.toString();
    }

    public static String invalidCommand() {
        return "Invalid command, please try again.";
    }
}
