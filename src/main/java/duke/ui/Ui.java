package duke.ui;

import java.util.Scanner;

/**
 * User interface class responsible for interacting with the user.
 * Provides methods to display messages, read user commands, and show errors.
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Adds a horizontal line to the provided message for better formatting.
     *
     * @param message The message to which a horizontal line is added.
     * @return The formatted message with a horizontal line.
     */
    private String addLine(String message) {
        String horizontal = "_____________________________________________________\n";
        return horizontal + message + "\n" + horizontal;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(addLine("Hello! I'm Ace\nWhat can I do for you?"));
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void sendMessage(String message) {
        System.out.println(addLine(message));
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(addLine(message));
    }
}
