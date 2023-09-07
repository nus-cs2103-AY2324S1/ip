package duke;

import java.util.Scanner;

/**
 * The duke.Ui class handles user interactions and displays messages to the user.
 */
public class Ui {
    private Scanner scanner;
    private String logo = "Wiz";

    /**
     * Constructs a new duke.Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        showLine();
        System.out.println("Hello from " + logo +
                "\nWhat can I do for you?");
        showLine();
    }

    /**
     * Reads a command input from the user.
     *
     * @return The user's input command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println("--------------------------");
    }

    /**
     * Displays a loading error message to the user.
     */
    public void showLoadingError() {
        System.out.println("\nContinue with new inputs please.");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showByeMessage() {
        showLine();
        System.out.println("Bye!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println("☹ OOPS!!! " + errorMessage);
    }

    /**
     * Closes the scanner to release system resources.
     */
    public void closeScanner() {
        scanner.close();
    }
}
