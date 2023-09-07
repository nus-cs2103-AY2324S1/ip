package dukduk;

import java.util.ArrayList;

/**
 * Represents the user interface (UI) for the Dukduk chatbot and provides methods 
 * for printing messages.
 */
public class Ui {

    /**
     * Prints a greeting message when the application starts.
     */
    public static void printGreetings() {
        printLine();
        System.out.println(" Hello! I'm Dukduk");
        System.out.println(" What can I do for you?");
        printLine();
    }

    /**
     * Prints a message when the user exits the application.
     */
    public static void printExit() {
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints a list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     */
    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Prints a message when a task is added to the list.
     *
     * @param tasks The list of tasks after adding a new task.
     */
    public static void addTask(ArrayList<Task> tasks) {
        System.out.println(" Got it. I've added this task:\n   "
                + tasks.get(tasks.size() - 1).toString());
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints a message when a task is deleted from the list.
     *
     * @param tasks       The list of tasks after deleting a task.
     * @param taskIndex   The index of the deleted task.
     * @param removedTask The removed task.
     */
    public static void deleteTask(ArrayList<Task> tasks, int taskIndex, Task removedTask) {
        System.out.println(" Noted. I've removed this task:\n   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints a message when a task is marked as done.
     *
     * @param tasks     The list of tasks after marking a task as done.
     * @param taskIndex The index of the marked task.
     */
    public static void markAsDone(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(" Nice! I've marked this task as done:\n ["
                + tasks.get(taskIndex).getStatusIcon() + "] "
                + tasks.get(taskIndex).getDescription());
    }

    /**
     * Prints a message when a task is marked as not done.
     *
     * @param tasks     The list of tasks after marking a task as not done.
     * @param taskIndex The index of the marked task.
     */
    public static void markAsNotDone(ArrayList<Task> tasks, int taskIndex) {
        System.out.println(" OK, I've marked this task as not done yet:\n ["
                + tasks.get(taskIndex).getStatusIcon() + "] "
                + tasks.get(taskIndex).getDescription());
    }

    /**
     * Prints a list of tasks found from find method, else prints no tasks found.
     *
     * @param matchingTasks The list of matching tasks to be printed.
     */
    public static void printTasksIfFound(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Prints a line separator to enhance readability.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message when an exception occurs.
     *
     * @param e The DukdukException that occurred.
     */
    public static void printErrorMsg(DukdukException e) {
        System.out.println(" ☹ " + e.getMessage());
    }
}
