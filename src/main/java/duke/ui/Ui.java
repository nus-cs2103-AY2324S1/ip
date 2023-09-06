package duke.ui;

import java.util.ArrayList;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface for interacting with the task list.
 */
public class Ui {

    /**
     * Generates a welcome message.
     *
     * @return A welcome message.
     */
    public static String greet() {
        return "Hello! I'm Paimon\nWhat can I do for you?";
    }

    /**
     * Generates a goodbye message.
     *
     * @return A goodbye message.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates a message confirming the addition of a task.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks after addition.
     * @return A message confirming the addition of the task.
     */
    public static String addTask(Task task, int taskCount) {
        String a = "Got it. I've added this task:\n";
        String c = "\nNow you have " + taskCount + " tasks in the list.";
        return a + task.toString() + c;
    }

    /**
     * Generates a message confirming the marking of a task as done.
     *
     * @param task The task that was marked as done.
     * @return A message confirming the marking of the task as done.
     */
    public static String markTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Generates a message confirming the marking of a task as not done.
     *
     * @param task The task that was marked as not done.
     * @return A message confirming the marking of the task as not done.
     */
    public static String unMarkTask(Task task) {
        return "Ok, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Generates a message confirming the removal of a task.
     *
     * @param desc  The task description.
     * @param count The total number of tasks after removal.
     * @return A message confirming the removal of the task.
     */
    public static String deleteTask(String desc, int count) {
        String a = "Noted. I've removed this task:\n";
        String c = "\nNow you have " + count + " tasks in the list.";
        return a + desc + c;
    }

    /**
     * Generates a message indicating the creation of a new task list.
     *
     * @return A message indicating the creation of a new task list.
     */
    public static String noTaskList() {
        return "No existing tasks, creating new task list...";
    }

    /**
     * Generates a message listing matching tasks.
     *
     * @param tasks The list of matching tasks.
     * @return A message listing the matching tasks.
     */
    public static String findTasks(ArrayList<Task> tasks) {
        String ans = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            ans += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return ans;
    }

    /**
     * Generates a message listing all tasks in a task list.
     *
     * @param taskList The task list containing the tasks.
     * @return A message listing all tasks in the task list.
     */
    public static String listTasks(TaskList taskList) {
        String ans = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.taskCount(); i++) {
            ans += (i + 1) + "." + taskList.getTask(i).toString() + "\n";
        }
        return ans;
    }

    /**
     * Generates an error message for a Duke exception.
     *
     * @param e The Duke exception.
     * @return An error message for the Duke exception.
     */
    public static String showError(DukeException e) {
        return e.toString();
    }
}
