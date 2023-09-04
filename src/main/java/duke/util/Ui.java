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
     *
     * @return A string continaing the welcome message.
     */
    public static String showWelcomeMessage() {
        String output = "";
        output += HORIZONTAL_LINE + "\n";
        output += "Hello! I'm " + name + "\n";
        output += "What can I do for you?\n";
        output += HORIZONTAL_LINE + "\n";
        return output;
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
     *
     * @return A string containing the exit message.
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon !\n";
    }

    /**
     * Displays a horizontal line for formatting purposes.
     *
     * @return A string representing a horizontal line.
     */
    public String showLine() {
        return HORIZONTAL_LINE + "\n";
    }
}
