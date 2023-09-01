package simon;

import java.util.Scanner;
import simon.task.Task;

/**
 * The {@code Ui} class handles interactions with the user by displaying
 * messages and reading user input.
 */
public class Ui {

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println(Simon.SPACEN + SimonAscii.toStr());
        System.out.println("Hello! I'm Simon\nWhat can I do for you?\n" + Simon.SPACE);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!" + Simon.NSPACE);
    }

    /**
     * Displays an error message indicating a loading error.
     */
    public void showLoadingError() {
        System.out.println("Data file not found. Starting with an empty task list." + Simon.NSPACE);
    }

    /**
     * Displays the given error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message + Simon.NSPACE);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task  The added task.
     * @param count The current number of tasks.
     */
    public void showAddedTask(Task task, int count) {
        System.out.println(Simon.SPACEN + "Got it. I've added this task:\n" + " " +
                task + String.format("\nNow you have %d %s in the list.",
                count, count > 1 ? "tasks" : "task") + Simon.NSPACE);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task  The deleted task.
     * @param count The current number of tasks.
     */
    public void showDeletedTask(Task task, int count) {
        System.out.println("Noted. I've removed this task:\n" + task + String.format("\nNow you have %d %s in the list.",
                count, count > 1 ? "tasks" : "task") + Simon.NSPACE);
    }

    /**
     * Displays a message indicating a task's status has been changed.
     *
     * @param marked True if the task is marked as done, false otherwise.
     * @param task   The task whose status has been changed.
     */
    public void showMarkedTask(boolean marked, Task task) {
        if (marked) {
            System.out.println("Nice! I've marked this task as done:\n " + task + Simon.NSPACE);
        } else {
            System.out.println("OK, I've marked this task as not done yet:\n " + task + Simon.NSPACE);
        }
    }

    /**
     * Displays a message indicating an unknown command was entered.
     */
    public void showUnknownCommand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + Simon.NSPACE);
    }

    /**
     * Lists all tasks in the provided task list.
     *
     * @param tasks The task list.
     */
    public void listTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(Simon.SPACE);
    }

    /**
     * Displays the tasks that match the given keyword.
     *
     * @param matchedTasks An ArrayList of tasks that match the keyword.
     */
    public void showMatchingTasks(TaskList matchedTasks) {
        System.out.println(Simon.SPACEN + "Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.getTaskCount(); i++) {
            System.out.println((i + 1) + "." + matchedTasks.getTask(i));
        }
        System.out.println(Simon.SPACE);
    }

    /**
     * Reads user input from the provided scanner.
     *
     * @param scanner The scanner to read input from.
     * @return The user's input as a string.
     */
    public String readInput(Scanner scanner) {
        return scanner.nextLine();
    }
}
