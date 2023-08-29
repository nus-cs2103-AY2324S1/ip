package duke;

import taskutil.Task;

/**
 * Contains methods to format text output for chatbot.
 */
public class Ui {

    /**
     * Formats chatbot output with borders, first line indentation and new line character at the end.
     * @param message Formatted message to be displayed to user.
     */
    public static void output(String message) {
        String line = "    ____________________________________________________________\n";
        String template = line + "     %s\n" + line;
        System.out.printf((template) + "%n", message);
    }

    /**
     * Formats chatbot output specifically for adding/removing tasks.
     * @param task Task added to list.
     */
    public static void taskOutput(Task task, String action, int size) {
        String taskMessage = "I've %s the following task as requested:\n       %s\n     "
                + "There are currently %d tasks in your list.";
        Ui.output(String.format(taskMessage, action, task.toString(), size));
    }
}
