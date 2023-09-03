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
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________\n");
        System.out.println("Hello! I 'm Jarvis.\n");
        System.out.println("What can I do for you?\n");
        System.out.println("_____________________________________\n");
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
     * Displays a line to format the UI.
     */
    public void showLine() {
        System.out.println("______________________________");
    }

    /**
     * Displays an error message when there's an issue loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty task list.");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Returns the Goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }


    /**
     * Displays the message for a deleted task.
     */
    public void showDeletedTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message for an added task.
     */
    public void showAddedTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message for a marked task.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays the message for an unmarked task.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays tasks that match the search keyword.
     *
     * @param tasks List of matched tasks.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
}
