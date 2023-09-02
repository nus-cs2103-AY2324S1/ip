package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the user interface of the chat bot.
 */
public class Ui {

    /**
     * Greets the user.
     */
    public static void greet() {
        System.out.println("Hello! I'm Siyuan");
        System.out.println("What can I do for you?");
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the error message.
     * @param err The error message.
     */
    public static void printError(DukeException err) {
        System.out.println(err.toString());
    }

    /**
     * Adds a Task object to the specified list of tasks.
     * @param tsk
     * @param taskNumber
     */
    public static void addTask(Task tsk, int taskNumber) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tsk.toString());
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
    }

    /**
     * Deletes a Task object from the specified list of tasks.
     * @param tsk The task to be deleted.
     * @param taskNumber The number of tasks in the list.
     */
    public static void deleteTask(Task tsk, int taskNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tsk.toString());
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
    }

    /**
     * Lists all the tasks in the specified list of tasks.
     * @param tasks The list of tasks.
     */
    public static void lsitAllTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Marks a Task object in the specified list of tasks as done.
     * @param tsk The task to be marked as done.
     */
    public static void markAsDone(Task tsk) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tsk.toString());
    }

    /**
     * Marks a Task object in the specified list of tasks as not done.
     * @param tsk The task to be marked as not done.
     */
    public static void markAsUndone(Task tsk) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tsk.toString());
    }

    /**
     * Finds the tasks that contain the specified keyword.
     * @param tsk The list of tasks that contain the specified keyword.
     */
    public static void findTasks(ArrayList<Task> tsk) {
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tsk) {
            System.out.println(task.toString());
        }
    }
}
