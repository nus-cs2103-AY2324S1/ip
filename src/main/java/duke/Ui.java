package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Handles user interactions and displays messages to the user.
 */
public class Ui {
    /**
     * Displays a greeting message to the user.
     */
    public static String hello() {
        return "Hello! I'm froggie! \n" + "What can I do for you?";
    }

    public static String showList(Task task, ArrayList<Task> arr) {
        return "Got it. I've added this task: \n" + "\t" + task.toString() + "\n" + "Now you have " + arr.size() + " tasks in the list";
    }

    public static String printDeletedTask(Task task, ArrayList<Task> arr) {
        return "Got it. I've deleted this task: \n" + "\t" + task.toString() + "\n" + "Now you have " + arr.size() + " tasks in the list";
    }

    public static String printMarked(Task task, boolean toMark) {
        if (toMark) {
            return "Nice! I've marked this task as done: \n" + "\t" + task.toString();
        } else {
            return "OK, I've marked this task as not done yet: \n" + "\t" + task.toString();
        }
    }
    /**
     * Displays a goodbye message to the user.
     */
    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
