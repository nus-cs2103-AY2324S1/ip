package bellcurvegod.gui;

import java.util.ArrayList;

import bellcurvegod.task.Task;

/**
 * Encapsulates all messages shown by the GUI.
 */
public class Gui {

    public static String getWelcomeMessage() {
        return "Hello! I'm Bell Curve God.\n" + "What can I do for you?\n"
            + "Type 'help' to see a list of all the commands.\n";
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

    public static String getMarkMessage(Task... tasks) {
        if (tasks.length == 1) {
            return "Nice! I've marked this task as done:\n" + tasks[0] + "\n";
        }
        String message = "Nice! I've marked the following tasks as done:\n";
        for (Task t : tasks) {
            message += "- " + t + "\n";
        }
        return message;
    }

    public static String getUnmarkMessage(Task... tasks) {
        if (tasks.length == 1) {
            return "OK, I've marked this task as not done yet:\n" + tasks[0] + "\n";
        }
        String message = "OK, I've marked  the following tasks as not done yet:\n";
        for (Task t : tasks) {
            message += "- " + t + "\n";
        }
        return message;
    }

    public static String getAddTaskMessage(Task newTask, int numOfTasks) {
        String message = "";
        message += "Got it. I've added this task:\n"
            + newTask + "\n"
            + "Now you have " + numOfTasks + " tasks in the list.\n";
        return message;
    }

    public static String getDeleteTaskMessage(int numOfTasks, Task... tasks) {
        String message = "Noted. I've removed this task:\n";
        for (Task t : tasks) {
            message += "- " + t + "\n";
        }

        if (numOfTasks <= 1) {
            message += "Now you have " + numOfTasks + " task in the list.\n";
        } else {
            message += "Now you have " + numOfTasks + " tasks in the list.\n";
        }
        return message;
    }

    public static String getWrongDateFormatMessage() {
        String message = "";
        message += "Please enter your time in the following format:\n"
            + "yyyy-mm-dd\n"
            + "E.g. 2019-10-15\n"
            + "You may also want to check whether your date is valid, e.g. 02-30 does not exist.\n";
        return message;
    }
}
