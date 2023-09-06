package duke.ui;

import java.util.Scanner;

/**
 * The Ui class is responsible for managing the user interface of the Duke application.
 * It provides methods for displaying messages, reading user input, and showing error messages.
 */
public class Ui {
    private String name = "Bob";
    private final String horizontal = "____________________________________________________________";
    private Scanner sc;

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
    public void showWelcome() {
        String ln1 = String.format("Hello! I'm %s", this.name);
        String ln2 = "What can I do for you?";
        String ln3 = ln1 + "\n" + ln2;

        System.out.println(messageCard(ln3));
    }

    /**
     * Displays a farewell message when the user exits the Duke application.
     */
    public void bye() {
        String ln3 = "Bye. Hope to see you again soon!";
        System.out.println(messageCard(ln3));
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
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays a horizontal line to separate different sections of the UI.
     */
    public void showLine() {
        System.out.println(horizontal);
    }

    /**
     * Prints a message in a formatted message card.
     *
     * @param msg The message to be displayed.
     */
    public void printMessage(String msg) {
        System.out.println(messageCard(msg));
    }

    /**
     * Reads a command from the user through the console input.
     *
     * @return The user's input command as a String.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }
}
