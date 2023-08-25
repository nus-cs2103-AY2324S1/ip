/**
 * The Ui class handles user interface interactions and displays messages to the user.
 */
public class Ui {
    /**
     * Displays a greeting message to the user.
     */
    public static void greet() {
        System.out.println("Hello! I'm Untitled!");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays an exit message to the user.
     */
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message after adding a task.
     *
     * @param task The task that was added.
     * @param newSize The new size of the task list.
     */
    public static void add(Task task, int newSize) {
        System.out.println("Got it! I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Displays a message after deleting a task.
     *
     * @param task The task that was deleted.
     * @param newSize The new size of the task list.
     */
    public static void delete(Task task, int newSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Displays a message after marking a task as done.
     *
     * @param task The task that was marked as done.
     */
    public static void mark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Displays a message after marking a task as not done.
     *
     * @param task The task that was marked as not done.
     */
    public static void unmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }
}
