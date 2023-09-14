package bellcurvegod.gui;

import java.util.ArrayList;

import bellcurvegod.task.Task;

/**
 * Encapsulates all messages shown by the GUI.
 */
public class Gui {

    /**
     * Greets the user.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm Bell Curve God.\n" + "What can I do for you?\n";
    }

    public static String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    public static String getFindMessage(ArrayList<Task> matchingTasks) {
        String message = "";
        if (!matchingTasks.isEmpty()) {
            message += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                message += (i + 1) + "." + matchingTasks.get(i) + "\n";
            }
        } else {
            return "There is no task in your list that matches this keyword.\n";
        }
        return message;
    }

    public static String getListMessage(ArrayList<Task> tasks) {
        String message = "";
        if (!tasks.isEmpty()) {
            message += "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                message += (i + 1) + "." + tasks.get(i) + "\n";
            }
        } else {
            return "There is no task in your list.\n";
        }
        return message;
    }

    public static String getMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    public static String getUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }

    public static String getAddTaskMessage(Task newTask, int numOfTasks) {
        String message = "";
        message += "Got it. I've added this task:\n"
            + newTask + "\n"
            + "Now you have " + numOfTasks + " tasks in the list.\n";
        return message;
    }

    public static String getDeleteTaskMessage(Task task, int numOfTasks) {
        String message = "";
        message += "Noted. I've removed this task:\n"
            + task + "\n"
            + "Now you have " + numOfTasks + " tasks in the list.\n";
        return message;
    }

    public static String getWrongDateFormatMessage() {
        String message = "";
        message += "Please enter your time in the following format:\n"
            + "yyyy-mm-dd\n"
            + "E.g. 2019-10-15\n";
        return message;
    }
}
