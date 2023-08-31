package ui;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user, displaying messages and reading input.
 */
public class Ui {
    private static String botName = "cc";
    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message upon starting the application.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the exit message before closing the application.
     */
    public void exit() {
        System.out.print("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * Displays a given message.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads a command input from the user.
     *
     * @return The command input provided by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
