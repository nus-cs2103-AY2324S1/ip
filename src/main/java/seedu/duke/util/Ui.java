package seedu.duke.util;

import java.util.Scanner;

/**
 * The Ui class handles the displaying of messages to the user.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hi! I'm TaskMate.\n"
                + "What can I do for you today?");
        printLine();
    }

    /**
     * Displays a goodbye message and closes the scanner.
     */
    public void showGoodbye() {
        System.out.println("Goodbye! See you next time!");
        printLine();
        scanner.close();
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
        printLine();
    }

    /**
     * Displays a message.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Retrieves user input from the console.
     *
     * @return The user's input.
     */
    public String getUserInput() {
        System.out.println("Enter command: ");
        return this.scanner.nextLine();
    }

    /**
     * Prints a horizontal line to separate printed messages.
     */
    public void printLine() {
        System.out.println("___________________________________________________________________");
    }
}
