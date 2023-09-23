package ui;

import java.util.Scanner;

/**
 * Represents the User Interface (UI) of the application.
 * This class handles input from and output to the user.
 */
public class UI {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints horizontal lines for formatting purposes.
     */
    public void horizontalLines() {
        System.out.println("__________________________________________________________________");
    }

    /**
     * Prints a message enclosed between two horizontal lines.
     *
     * @param message The message to be displayed between horizontal lines.
     */
    public void lineSandwich(String message) {
        horizontalLines();
        display(message);
        horizontalLines();
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The line of input as a string.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void display(String message) {
        System.out.println(message);
    }

    /**
     * Displays the opening message to greet the user when the application starts.
     */
    public void openingMessage() {
        lineSandwich(" Hello there. I'm Whelmed.\n What do you want?");
    }
}
