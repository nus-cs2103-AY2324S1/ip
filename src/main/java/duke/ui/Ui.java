package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Deals with interactions with the user
 * Return messages to user from Duke.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor for Ui class. Initializes the Scanner object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the welcome greetings.
     */
    public static String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return "Hello from\n" + logo
                + "_____________________________________\n"
                + "Hello! I 'm Jarvis.\n"
                + "What can I do for you?\n"
                + "_____________________________________\n";
    }

    /**
     * Reads a command entered by the user.
     *
     * @return A string representing the user's command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns a line to format the UI.
     */
    public String showLine() {
        return "______________________________";
    }

    /**
     * Returns an error message when there's an issue loading tasks.
     */
    public String showLoadingError() {
        return "Error loading tasks. Starting with an empty task list.";
    }

    /**
     * Returns an error message.
     *
     * @param message The error message.
     */
    public String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Returns the Goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the message for a deleted task.
     */
    public String showDeletedTask(Task task) {
        return "Noted. I've removed this task:\n  " + task;
    }

    /**
     * Returns the message for an added task.
     */
    public String showAddedTask(Task task) {
        return "Got it. I've added this task:\n  " + task;
    }

    /**
     * Returns the message for a marked task.
     */
    public String showMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Returns the message for an unmarked task.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns tasks that match the search keyword.
     *
     * @param tasks List of matched tasks.
     */
    public String showFoundTasks(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return result.toString();
    }
}
