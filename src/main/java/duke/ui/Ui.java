package duke.ui;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Task;

/**
 * Represents the user interface of the chat bot.
 */
public class Ui {

    /**
     * Greets the user.
     */
    public static String greet() {
        return "Hello! I'm Siyuan\nWhat can I do for you?";
    }

    /**
     * Exits the program.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the error message.
     * 
     * @param err The error message.
     */
    public static String returnErrorString(DukeException err) {
        return err.toString();
    }

    /**
     * Adds a Task object to the specified list of tasks.
     * @param tsk
     * @param taskNumber
     */
    public static String addTask(Task tsk, int taskNumber) {
        return "Got it. I've added this task:\n" + tsk.toString() + "\nNow you have " +
                taskNumber + " tasks in the list.";
    }

    /**
     * Deletes a Task object from the specified list of tasks.
     * 
     * @param tsk The task to be deleted.
     * @param taskNumber The number of tasks in the list.
     */
    public static String deleteTask(Task tsk, int taskNumber) {
        return "Noted. I've removed this task:\n" + tsk.toString() + "\nNow you have "
                + taskNumber + " tasks in the list.";
    }

    /**
     * Lists all the tasks in the specified list of tasks.
     * 
     * @param tasks The list of tasks.
     */
    public static String listAllTasks(ArrayList<Task> tasks) {
        String res = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            res += "\n" + (i + 1) + ". " + tasks.get(i).toString();
        }
        return res;
    }

    /**
     * Marks a Task object in the specified list of tasks as done.
     * 
     * @param tsk The task to be marked as done.
     */
    public static String markAsDone(Task tsk) {
        return "Nice! I've marked this task as done:\n" + tsk.toString();
    }

    /**
     * Marks a Task object in the specified list of tasks as not done.
     * 
     * @param tsk The task to be marked as not done.
     */
    public static String markAsUndone(Task tsk) {
        return "OK, I've marked this task as not done yet:\n" + tsk.toString();
    }

    /**
     * Finds the tasks that contain the specified keyword.
     * 
     * @param tsk The list of tasks that contain the specified keyword.
     */
    public static String findTasks(ArrayList<Task> tsk) {
        String res = "Here are the matching tasks in your list:";
        for (Task task : tsk) {
            res += task.toString();
        }
        return res;
    }
}
