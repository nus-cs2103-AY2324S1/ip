package duke;

import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface for interacting with the task list.
 */
public class Ui {

    /**
     * Displays a greeting message.
     */
    public static void greetings() {
        showLine();
        System.out.println(" Hello! I'm Paimon");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Displays an exit message.
     */
    public static void exit() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The added task.
     * @param taskCount The current number of tasks in the list.
     */
    public static void addTask(Task task, int taskCount) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The marked task.
     */
    public static void markTask(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task.toString());
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The marked task.
     */
    public static void unMarkTask(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task.toString());
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The deleted task.
     * @param count The updated number of tasks in the list.
     */
    public static void deleteTask(Task task, int count) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + (count - 1) + " tasks in the list.");
    }

    /**
     * Displays a message indicating that no existing tasks were found.
     */
    public static void noTaskList() {
        showLine();
        System.out.println("No existing tasks, creating new task list...");
        showLine();
    }

    /**
     * Displays matching tasks based on a keyword search.
     *
     * @param tasks The list of matching tasks.
     */
    public static void findTasks(ArrayList<Task> tasks) {
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Displays the list of tasks in the task list.
     *
     * @param taskList The task list to display.
     */
    public static void listTasks(TaskList taskList) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.taskCount(); i++) {
            System.out.println(" " + (i + 1) + "." + taskList.getTask(i).toString());
        }
    }

    /**
     * Displays a separator line.
     */
    public static void showLine() {
        System.out.println("---------------------------------------------------------------");
    }

    /**
     * Displays an error message for a DukeException.
     *
     * @param e The DukeException to display.
     */
    public static void showError(DukeException e) {
        System.out.println(e.toString());
    }
}
