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
     * Reads the next line of input from the user.
     * @return The next line of input from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the welcome message.
     */
    public void showError(String errorMessage) {
        printMessage(errorMessage);
    }
}
