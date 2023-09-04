package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface of the Duke application.
 * This class is responsible for displaying messages and reading user input.
 */
public class Ui {

    private String chatBotName = "Carl";
    private Scanner scanner;

    /**
     * Constructs an Ui object and initializes the Scanner for reading user input from the console.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Formats a given message into a stylized card with a horizontal line.
     *
     * @param message The message to be displayed in the card.
     * @return The stylized message card.
     */
    private String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;
    }

    /**
     * Displays a message in the message card.
     *
     * @param message The message to be displayed.
     */
    public void sendMessage(String message) {
        System.out.println(messageCard(message));
    }

    /**
     * Displays an error message for loading tasks from a file.
     */
    public void showLoadingError() {
        System.out.println((messageCard("Carl: Error Loading File")));
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println((messageCard(message)));
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println(messageCard("Hello! I'm " + chatBotName
                + "\n\t What can I do for you?"));
    }

    /**
     * Reads and retrieves a command from the user.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        String userInput = this.scanner.nextLine();
        return userInput;
    }
}
