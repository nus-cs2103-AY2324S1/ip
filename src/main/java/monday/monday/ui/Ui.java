package monday.monday.ui;

import monday.task.Task;

/**
 * Ui class is responsible for handling user interface related functions.
 */
public class Ui {
    /**
     * Prints a greeting message to the console.
     */
    public static String greet() {
        return ("Hello! I'm Monday\nWhat can I do for you?");
    }

    /**
     * Prints a farewell message to the console.
     */
    public static String exit() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a separator line to the console.
     */
    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints message for marking a task as done.
     *
     * @param task The task  marked as done.
     * @return A string indicating that the task has been marked as done.
     */
    public static String markTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints message unMarking a task as not done yet.
     *
     * @param task The task marked as not done yet.
     * @return A string indicating that the task has been marked as not done yet.
     */
    public static String markAsUndone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Prints message for adding a task to the list.
     *
     * @param task The task added to the list.
     * @param size The number of tasks in the list after the new task is added.
     * @return A string indicating that the task has been added to the list and the new size of the list.
     */
    public static String addTask(Task task, int size) {
        return ("Got it. I've added this task:\n    " + task.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints message for deleting a task from the list.
     *
     * @param task The task deleted from the list.
     * @param size The number of tasks in the list after the task is deleted.
     * @return A string indicating that the task has been removed from the list and the new size of the list.
     */
    public static String deleteTask(Task task, int size) {
        return ("Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }
    /**
     * Prints an error message along with the exception message.
     *
     * @param message the error message to be printed.
     * @param e the exception object containing the error details.
     */
    public static void printErrorMessage(String message, Exception e) {
        System.out.println(message + e.getMessage());
    }
}
