package duke;

import java.util.ArrayList;

/**
 * Encapsulates ui for chat bot.
 */
public class Ui {

    /**
     * Prints welcome message.
     */
    public static String welcomeMessage() {
        return " Hello! I'm Jarvis\n"
                + " What can I do for you?";
    }

    /**
     * Prints all tasks in task list.
     *
     * @param tasks The task list.
     */
    public static String listTasks(ArrayList<Task> tasks) {
        String result = " Here are the tasks in your list:";
        int counter = 0;
        while (counter != tasks.size()) {
            counter++;
            result += "\n" + " " + counter + "."
                    + tasks.get(counter - 1).toString();
        }
        return result;
    }

    /**
     * Prints matching tasks in task list.
     *
     * @param tasks The task list.
     */
    public static String listMatchingTasks(ArrayList<Task> tasks) {
        String result = " Here are the matching tasks in your list:";
        int counter = 0;
        while (counter != tasks.size()) {
            counter++;
            result += "\n" + " " + counter + "."
                    + tasks.get(counter - 1).toString();
        }
        return result;
    }

    /**
     * Prints message for marking a task.
     *
     * @param t The task marked.
     */
    public static String markTask(Task t) {
        return " Nice! I've marked this task as done:\n"
                + "  " + t.toString();
    }

    /**
     * Prints the message for adding a task.
     *
     * @param t The task added.
     * @param taskList The task list of the task.
     */
    public static String addTask(Task t, ArrayList<Task> taskList) {
        return " Got it. I've added this task:\n"
                + "   " + t.toString() + "\n"
                + " Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Prints the message for deleting a task.
     *
     * @param t The task deleted.
     * @param taskList The task list of the task.
     */
    public static String deleteTask(Task t, ArrayList<Task> taskList) {
        return " Noted. I've removed this task:\n"
                + "   " + t.toString() +"\n"
                + " Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Prints the exception message.
     *
     * @param e The exception printed.
     */
    public static String printException(DukeException e) {
        return " " + e.getMessage();
    }

    /**
     * Prints farewell message.
     */
    public static String farewellMessage() {
        return " Bye. Hope to see you again soon!";
    }
}
