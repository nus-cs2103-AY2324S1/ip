package duke;

import java.util.Scanner;

/**
 * Represents a user interface for the user to input their commands and the bot to reply a message.
 */
public class Ui {

    /** A divider string. */
    private static final String DIVIDER = "\t____________________________________________________________";

    /**
     * Prints a welcome message to the user.
     */
    public void showWelcome() {
        String introMessage = "\t____________________________________________________________\n"
                + "\t Hello! I'm Bob the Chatbot!\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________";

        System.out.println(introMessage);
    }

    /**
     * Prints a line containing a divider.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Reads the next line from the specified Scanner.
     *
     * @param sc The scanner to read the next line from.
     * @return The next line read from the Scanner, as a String.
     */
    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints an error message, formatted for the chatbot.
     *
     * @param errorMsg The error message to be printed.
     */
    public void showError(String errorMsg) {
        print("☹ OOPS!!! " + errorMsg);
    }

    /**
     * Prints a message, formatted for the chatbot.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        System.out.println("\t " + message);
    }
}
