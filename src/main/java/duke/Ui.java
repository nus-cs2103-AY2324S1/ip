package duke;

import taskutil.Task;

/**
 * Contains methods to format text output when chatbot adds/deletes a task.
 */
public class Ui {

    private static String outMessage;

    /**
     * Formats chatbot output specifically for adding/removing tasks.
     *
     * @param task Task added to list.
     */
    public static void taskOutput(Task task, Action action, int size) {
        String taskMessage = "I've %s the following task as requested:\n       %s\n"
                + "There are currently %d tasks in your list.";
        if (action == Action.ADD) {
            outMessage = String.format(taskMessage, "added", task.toString(), size);
        } else if (action == Action.REMOVE) {
            outMessage = String.format(taskMessage, "removed", task.toString(), size);
        }
    }

    /**
     * Returns chatbot output after adding task.
     *
     * @return Chatbot output.
     */
    public static String getOutMessage() {
        return outMessage;
    }

    /**
     * Sets chatbout output after adding task.
     *
     * @param outMessage Message to be output.
     */
    public static void setOutMessage(String outMessage) {
        Ui.outMessage = outMessage;
    }

    /**
     * Enum to denote whether task was added or removed from tasklist.
     */
    public enum Action {
        ADD,
        REMOVE
    }
}
