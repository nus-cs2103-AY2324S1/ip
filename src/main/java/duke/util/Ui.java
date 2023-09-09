package duke.util;

import java.util.Scanner;

/**
 * The Ui class handles the displaying of messages to the user.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class Ui {
    //public static final String HORIZONTAL_LINE = "___________________________________________________________________";
    private Scanner scanner;

    /**
     * Constructs an Ui and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message.
     */
    public String welcomeMessage() {
        return "Hi! I'm TaskMate.\nWhat can I do for you today?";
    }

    /**
     * Displays a goodbye message and closes the scanner.
     */
    public String goodbyeMessage() {
        scanner.close();
        return "Goodbye! See you next time!";
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
}
