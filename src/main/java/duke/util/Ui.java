package duke.util;

import java.util.Scanner;

/**
 * Represents the user interface for managing input and output to the console.
 * Provides methods to show welcome and exit messages, get user input, and display a horizontal line.
 */
public class Ui {

    /** The horizontal line used for formatting output */
    private static final String HORIZONTAL_LINE = "----------------------------------------";

    /** The name of the bot */
    private static final String name = "Code Buddy";
    /** Scanner object to read user input */
    private Scanner inputScanner;
    /**
     * Initializes a new Ui object, setting up the scanner for user input.
     */
    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Retrieves a line of input from the user.
     *
     * @return A string containing the user's input.
     */
    public String getUserInput() {
        return inputScanner.nextLine();
    }

    /**
     * Displays the exit message to the user.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon !");
    }

    /**
     * Displays a horizontal line for formatting purposes.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
