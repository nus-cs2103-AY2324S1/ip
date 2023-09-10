package bert.ui;

import bert.common.Messages;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private final Scanner sc;

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

    public void showLoadingError() {
        showToUser(
                DIVIDER,
                Messages.MESSAGE_LOADING_ERROR,
                DIVIDER
        );
    }

    public void showError(String errorMessage) {
        showToUser(
                DIVIDER,
                String.format(Messages.MESSAGE_ERROR, errorMessage),
                DIVIDER
        );
    }

    public void showResult(String message) {
        showToUser(
                DIVIDER,
                message,
                DIVIDER
        );
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }
}
