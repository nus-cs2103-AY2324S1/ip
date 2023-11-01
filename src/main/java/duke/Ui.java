package duke;

import java.util.Scanner;

/**
 * Represents the user interface of the chat bot.
 * A <code>Ui</code> object corresponds to the user interface of the chat bot.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a <code>Ui</code> object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message.
     */
    public void showError(String errorMessage) {
        printMessage(errorMessage);
    }
}
