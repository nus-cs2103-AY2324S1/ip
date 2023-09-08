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
        System.out.println(Simon.SPACEN + SimonAscii.toStr());
        System.out.println("Hello! I'm Simon\nWhat can I do for you?\n" + Simon.SPACE);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        clearOutput();
        output.append("Bye. Hope to see you again soon!");
        System.out.println("Bye. Hope to see you again soon!" + Simon.NSPACE);
    }

    /**
     * Displays an error message indicating a loading error.
     */
    public void showLoadingError() {
        clearOutput();
        output.append("Data file not found. Starting with an empty task list.");
        System.out.println("Data file not found. Starting with an empty task list." + Simon.NSPACE);
    }

    /**
     * Displays the given error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        clearOutput();
        output.append(message);
        System.out.println(message + Simon.NSPACE);
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
        clearOutput();
        output.append("Noted. I've removed this task:\n" + task + String.format("\nNow you have %d %s in the list.",
                count, count > 1 ? "tasks" : "task"));
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
        clearOutput();
        if (marked) {
            output.append("Nice! I've marked this task as done:\n ").append(task);
            System.out.println("Nice! I've marked this task as done:\n " + task + Simon.NSPACE);
        } else {
            output.append("OK, I've marked this task as not done yet:\n ").append(task);
            System.out.println("OK, I've marked this task as not done yet:\n " + task + Simon.NSPACE);
        }
    }

    /**
     * Displays a message indicating an unknown command was entered.
     */
    public void showUnknownCommand() {
        clearOutput();
        output.append("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        output.append("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + Simon.NSPACE);
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
        clearOutput();
        output.append("Here are the matching tasks in your list:");
        System.out.println(Simon.SPACEN + "Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.getTaskCount(); i++) {
            output.append((i + 1)).append(".").append(matchedTasks.getTask(i));
            System.out.println((i + 1) + "." + matchedTasks.getTask(i));
        }
        System.out.println(Simon.SPACE);
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
