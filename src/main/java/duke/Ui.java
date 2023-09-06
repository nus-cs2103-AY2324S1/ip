package duke;

import taskutil.Task;

/**
 * Contains methods to format text output for chatbot.
 */
public class Ui {

    private static String outMessage;

    /**
     * Formats chatbot output specifically for adding/removing tasks.
     *
     * @param task Task added to list.
     */
    public static void taskOutput(Task task, Action action, int size) {
        String taskMessage = "I've %s the following task as requested:\n       %s\n     "
                + "There are currently %d tasks in your list.";
        if (action == Action.ADD) {
            Ui.setOutMessage(String.format(taskMessage, "added", task.toString(), size));
        } else if (action == Action.REMOVE) {
            Ui.setOutMessage(String.format(taskMessage, "removed", task.toString(), size));
        }
    }

    public static String getOutMessage() {
        return Ui.outMessage;
    }

    /**
     * Updates chatbot output.
     *
     * @param message Updated message to be displayed to user.
     */
    public static void setOutMessage(String message) {
        outMessage = message;
    }

    /**
     * Enum to denote whether task was added or removed from tasklist.
     */
    public enum Action {
        ADD,
        REMOVE
    }
}
