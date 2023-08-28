package carbonbot;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user such as receiving inputs and printing outputs.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a new Ui that takes input from System.in, and outputs to System.out
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a line of divider
     */
    public void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    /**
     * Blocks until a line is received from System.in, and returns the input.
     *
     * @return The input as a string
     */
    public String getNextInput() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a greeting to welcome the user and ask for input.
     */
    public void showGreetings() {
        printDivider();
        System.out.println("Hello! I'm CarbonBot");
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Displays the message through System.out.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the error message when the task list could not be loaded from disk.
     */
    public void showLoadingError() {
        System.out.println("Failed to load save file from disk.");
    }

    /**
     * Clears up the resources at the end of the Ui usage.
     */
    public void close() {
        this.scanner.close();
    }
}
