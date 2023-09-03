package carbonbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ui deals with interactions with the user such as receiving inputs and printing outputs.
 */
public class Ui {
    private final Scanner scanner;
    private final List<String> messageBuffer = new ArrayList<>();

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
     * Displays the message through System.out. Also adds the message to buffer.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
        bufferMessage(message);
    }

    /**
     * Adds the message to a buffer
     * @param message A string message
     */
    private void bufferMessage(String message) {
        messageBuffer.add(message);
    }

    /**
     * Returns the messages stored in the buffer and clears the buffer
     * @return Messages in the buffer
     */
    public String flushBuffer() {
        String combinedMessage = String.join("\n", messageBuffer);
        messageBuffer.clear();
        return combinedMessage;
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
