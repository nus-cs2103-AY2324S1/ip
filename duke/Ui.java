package duke;

import java.util.Scanner;

/**
 * The Ui class handles user interface interactions for the Duke chatbot.
 * It provides methods for greeting the user, displaying messages, getting user input,
 * and closing the input scanner.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object and initializes the input scanner to read user input from the console.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a goodbye message when the chatbot is about to exit.
     *
     * @return String representing an exit message
     */
    protected String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads and retrieves user input from the console.
     *
     * @return The user's input as a string.
     */
    protected String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the input scanner when it's no longer needed.
     */
    protected void closeScanner() {
        scanner.close();
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    protected void showMessage(String message) {
        System.out.println(message);
    }
}

