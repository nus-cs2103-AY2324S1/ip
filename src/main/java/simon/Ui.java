package simon;

import java.util.Scanner;
import simon.task.Task;

/**
 * The {@code Ui} class handles interactions with the user by displaying
 * messages and reading user input.
 */
public class Ui {
    private static StringBuilder output;

    public Ui() {
        this.output = new StringBuilder();
    }

    /**
     * Displays the welcome message.
     */
    public static void showWelcome() {
        clearOutput();
        output.append("Hello! I'm Simon\nWhat can I do for you?\n");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        clearOutput();
        output.append("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message indicating a loading error.
     */
    public void showLoadingError() {
        clearOutput();
        output.append("Data file not found. Starting with an empty task list.");
    }

    /**
     * Displays the given error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        clearOutput();
        output.append(message);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task  The added task.
     * @param count The current number of tasks.
     */
    public void showAddedTask(Task task, int count) {
        clearOutput();
        output.append("Got it. I've added this task:\n" + " " +
                task + String.format("\nNow you have %d %s in the list.",
                count, count > 1 ? "tasks" : "task"));
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task  The deleted task.
     * @param count The current number of tasks.
     */
    public void showDeletedTask(Task task, int count) {
        clearOutput();
        output.append("Noted. I've removed this task:\n" + task + String.format("\nNow you have %d %s in the list.",
                count, count > 1 ? "tasks" : "task"));
    }

    /**
     * Displays a message indicating a task's status has been changed.
     *
     * @param marked True if the task is marked as done, false otherwise.
     * @param task   The task whose status has been changed.
     */
    public void showMarkedTask(boolean marked, Task task) {
        clearOutput();
        if (marked) {
            output.append("Nice! I've marked this task as done:\n ").append(task);
        } else {
            output.append("OK, I've marked this task as not done yet:\n ").append(task);
        }
    }

    /**
     * Displays a message indicating an unknown command was entered.
     */
    public void showUnknownCommand() {
        clearOutput();
        output.append("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Lists all tasks in the provided task list.
     *
     * @param tasks The task list.
     */
    public void listTasks(TaskList tasks) {
        clearOutput();
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            output.append((i + 1) + "." + tasks.getTask(i) + "\n");
        }
    }

    /**
     * Displays the tasks that match the given keyword.
     *
     * @param matchedTasks An ArrayList of tasks that match the keyword.
     */
    public void showMatchingTasks(TaskList matchedTasks) {
        clearOutput();
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchedTasks.getTaskCount(); i++) {
            output.append((i + 1)).append(".").append(matchedTasks.getTask(i)).append("\n");
        }
    }

    /**
     * Returns the next line of input from the user.
     *
     * @return The next line of input from the user.
     */
    public String getOutput() {
        return output.toString();
    }

    /**
     * Clears the output.
     */
    public static void clearOutput() {
        output = new StringBuilder();
    }
}
