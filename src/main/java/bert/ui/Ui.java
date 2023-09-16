package bert.ui;

import bert.common.Messages;

import java.util.Scanner;

/**
 * Represents a ui which deals with interactions with the user.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner sc;

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        showToUser(
                DIVIDER,
                Messages.MESSAGE_WELCOME,
                DIVIDER
        );
    }

    /**
     * Generates and prints the exit message upon the end of the application.
     */
    public void showExit() {
        showToUser(
                DIVIDER,
                Messages.MESSAGE_GOODBYE,
                DIVIDER
        );
    }

    /**
     * Generates and prints the error message when an error occurs during loading.
     */
    public void showLoadingError() {
        showToUser(
                DIVIDER,
                Messages.MESSAGE_LOADING_ERROR,
                DIVIDER
        );
    }

    /**
     * Generates and prints the error message when an error occurs in the program.
     *
     * @param errorMessage the description of the error
     */
    public void showError(String errorMessage) {
        showToUser(
                DIVIDER,
                String.format(Messages.MESSAGE_ERROR, errorMessage),
                DIVIDER
        );
    }

    /**
     * Generates and prints the result of a successful command execution.
     *
     * @param message the message provided by the command
     */
    public void showResult(String message) {
        showToUser(
                DIVIDER,
                message,
                DIVIDER
        );
    }

    /**
     * Generates and prints messages to the user.
     *
     * @param message input strings which will be printed out to the user
     */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /**
     * Reads the entire line of user input.
     *
     * @return a string representation of the user input
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }
}
