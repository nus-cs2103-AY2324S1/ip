package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static messages.Message.MESSAGE_WELCOME;
import static messages.Message.MESSAGE_EXIT;
import static messages.Message.MESSAGE_INSTRUCTIONS;

/**
 * This class handles all the user interactions for the Duke application,
 * responsible for receiving input and displaying output.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a Ui object capable of receiving inputs and displaying outputs.
     *
     * @param in A Scanner object that handles input.
     * @param out Logs output onto the console.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * A line divider.
     */
    private static final String LINE_DIVIDER = "────────────────────────────────────";

    /**
     * Prints the line divider onto the console.
     */
    public void printLineDivider() {
        out.println(LINE_DIVIDER);
    }

    /**
     * Prints the welcome message when the Duke application starts.
     *
     * @param version The current version of Duke implemented.
     */
    public void printWelcomeMessage(String version) {
        printToUser(version);
        printToUser(MESSAGE_WELCOME);
    }

    /**
     * Prints instructions on how to use the Duke application.
     */
    public void printInstructions() {
        printToUser(MESSAGE_INSTRUCTIONS);
    }

    /**
     * Prints the exit message upon exiting of the Duke application.
     */
    public void printExitMessage() {
        printToUser(MESSAGE_EXIT);
    }

    /**
     * Prints the correctly formatted output based on the user's actions.
     *
     * @param text The output.
     */
    public void printToUser(String text) {
        out.println(LINE_DIVIDER);
        out.println(text);
        out.println(LINE_DIVIDER);
    }

    /**
     * Returns true if the user input should be ignored by the application, false otherwise.
     *
     * @param input The user input.
     * @return true if ignored, false if not.
     */
    public boolean shouldIgnoreInput(String input) {
        return input.trim().isEmpty();
    }

    /**
     * Prompts the user to enter an input.
     *
     * @return The user input.
     */
    public String getUserCommand() {
        printLineDivider();
        out.println("Please enter command below:");

        String userInput = in.nextLine();

        while (shouldIgnoreInput(userInput)) {
            userInput = in.nextLine();
        }

        return userInput;
    }
}
