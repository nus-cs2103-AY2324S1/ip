package duke;

import duke.task.Task;

import java.util.List;

/**
 * The Ui class handles user interface interactions and displays messages to the user.
 */
public class Ui {

    /**
     * Displays a greeting message when the chatbot starts.
     */
    public static void greet() {
        System.out.println("---------------------------------------------\n Hello! I'm zy\n" +
                " What can I do for you?\n---------------------------------------------");
    }

    /**
     * Displays a goodbye message when the chatbot exits.
     */
    public static void goodbye() {
        System.out.println("---------------------------------------------\n Bye. Hope to see you again soon!" +
                "\n---------------------------------------------");
    }

    /**
     * Prints a horizontal line as a separator.
     */
    public static void printLine() {
        System.out.println("---------------------------------------------");
    }

    /**
     * Lists the tasks in the task list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public static void listTasks(List<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Displays a message when marking a task as done.
     */
    public static void markDoneMessage() {
        System.out.println(" Nice! I've marked this task as done:");
    }

    /**
     * Displays a message when marking a task as not done.
     */
    public static void markUndoneMessage() {
        System.out.println(" OK, I've marked this task as not done yet:");
    }

    /**
     * Displays information about a specific task.
     *
     * @param task The task to be displayed.
     */
    public static void showTaskMessage(Task task) {
        System.out.println("   " + task.toString());
    }

    /**
     * Displays a message after adding a task to the task list.
     *
     * @param tasks The task list containing the added task.
     */
    public static void addTaskMessage(TaskList tasks) {
        System.out.println(" Got it. I've added this task:" + "\n" + "   "
                + tasks.getAll().get(tasks.getAll().size() - 1).toString() + "\n" + " Now you have "
                + tasks.getAll().size() + " tasks in the list.");
    }

    /**
     * Displays a message when removing a task from the task list.
     */
    public static void removeTaskMessage() {
        System.out.println(" Noted. I've removed this task:");
    }

    /**
     * Lists and displays the tasks that match a specific keyword in the user's task list.
     *
     * @param matchingTasks A list of tasks that match the user's search keyword.
     */
    public static void listMatchedTasks(List<Task> matchingTasks) {
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + matchingTasks.get(i).toString());
        }
    }

    public static void showLoadingError(DukeException e) {
        System.out.println(e);
    }
}
