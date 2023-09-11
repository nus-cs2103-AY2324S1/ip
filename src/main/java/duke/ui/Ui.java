package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * The Ui class handles interactions with the user through the console.
 * It provides methods for reading user input, greeting the user, and displaying information.
 */
public class Ui {
    /**
     * Displays a greeting message to the user.
     */
    public static String greet() {
        return "Hello! I'm Bot!\n" + "What can I do for you?";
    }

    /**
     * Displays an exit message to the user.
     */
    public static String exit() {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param list The TaskList containing the tasks to be listed.
     */
    public static String listTasks(TaskList list) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            String str = i + 1 + ". " + list.get(i) + "\n";
            output.append(str);
        }
        return output.toString();
    }

    /**
     * Displays the details of an added task to the user.
     *
     * @param task The task that was added.
     */
    public static String addTask(Task task) {
        return "Got it. I've added this task:\n" +  task.toString();
    }

    /**
     * Displays the count of tasks in the list to the user.
     *
     * @param list The list of tasks to be counted.
     */
    public static String countTasks(TaskList list) {
        return "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Displays the details of a marked task to the user.
     *
     * @param task The task that was marked as done.
     */
    public static String markAsDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays the details of a deleted task to the user.
     *
     * @param task The task that was deleted.
     */
    public static String deleteTask(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Displays a list of matching tasks to the user.
     *
     * @param list The list of matching tasks to be displayed.
     */
    public static String findTasks(ArrayList<Task> list) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            String str = i + 1 + ". " + list.get(i) + "\n";
            output.append(str);
        }
        return output.toString();
    }
}
