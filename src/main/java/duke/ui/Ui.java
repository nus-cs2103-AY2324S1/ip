package duke.ui;

import java.util.Scanner;

import duke.task.Task;

/**
 * The Ui class is responsible for managing the user interface of the Duke application.
 * It provides methods for displaying messages, reading user input, and showing error messages.
 */
public class Ui {
    private static final String name = "Bob";
    private final String horizontal = "____________________________________________________________";
    private Scanner sc;

    private String latestMessage = "";

    private String latestCommand = "";

    private Task deletedTask;
    private int markedIndex;
    private int unmarkedIndex;

    /**
     * Constructs a Ui object with a Scanner to read user input from the console.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Generates a message card with a horizontal line for formatting.
     *
     * @param message The message to be displayed in the card.
     * @return A formatted message card containing the provided message.
     */
    public String messageCard(String message) {
        return horizontal + "\n" + message + "\n" + horizontal;
    }

    /**
     * Displays a welcome message when the Duke application is started.
     */
    public static String showWelcome() {
        String ln1 = String.format("Hello! I'm %s", Ui.name);
        String ln2 = "What can I do for you?";
        String ln3 = ln1 + "\n" + ln2;

        return ln3;
    }

    /**
     * Displays a farewell message when the user exits the Duke application.
     */
    public String showBye() {
        String ln3 = "Bye. Hope to see you again soon!";
        return ln3;
    }

    /**
     * Displays an error message indicating a loading error during application startup.
     */
    public void showLoadingError() {
        String error = this.name + ": Error encountered, loading screen failed!";
        System.out.println(error);;
    }

    /**
     * Displays an error message with the provided message.
     *
     * @param msg The error message to be displayed.
     */
    public String showError(String msg) {
        return msg;
    }

    /**
     * Retrieves the latest message displayed.
     *
     * @return The latest message.
     */
    public String retrieveMessage() {
        return this.latestMessage;
    }

    /**
     * Updates the latest message.
     *
     * @param msg The new message to be set as the latest message.
     */
    public void updateMessage(String msg) {
        this.latestMessage = msg;
    }

    /**
     * Updates the most recent user command.
     *
     * @param latest The new most recent user command to be set.
     */
    public void updateRecentCommand(String latest) {
        this.latestCommand = latest;
    }

    /**
     * Updates the index of the most recently marked task.
     *
     * @param index The index of the marked task.
     */
    public void updateLatestMarked(int index) {
        this.markedIndex = index;
    }

    /**
     * Updates the index of the most recently unmarked task.
     *
     * @param index The index of the unmarked task.
     */
    public void updateLatestUnmarked(int index) {
        this.unmarkedIndex = index;
    }

    /**
     * Updates the task that was most recently deleted.
     *
     * @param task The task that was deleted.
     */
    public void updateDeletedTask(Task task) {
        this.deletedTask = task;
    }

    /**
     * Retrieves the most recent user command.
     *
     * @return The most recent user command.
     */
    public String retrieveRecentCommand() {
        return this.latestCommand;
    }

    /**
     * Retrieves the index of the most recently marked task.
     *
     * @return The index of the marked task.
     */
    public int retrieveLatestMarked() {
        return this.markedIndex;
    }

    /**
     * Retrieves the index of the most recently unmarked task.
     *
     * @return The index of the unmarked task.
     */
    public int retrieveLatestUnmarked() {
        return this.unmarkedIndex;
    }

    /**
     * Retrieves the task that was most recently deleted.
     *
     * @return The task that was deleted.
     */
    public Task retrieveDeletedTask() {
        return this.deletedTask;
    }

}
