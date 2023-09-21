package rua.common;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private final Scanner in;

    private static final String DIVIDING_LINE = "____________________________________________________________";

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Shows the dividing line.
     */
    public void showLine() {
        StringLogger.append(DIVIDING_LINE);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        final String welcomeMessage = " Hello! I'm Rua, your ChatBot\n"
                + " What can I do for you?\n";
        StringLogger.append(DIVIDING_LINE + "\n" + welcomeMessage
                + DIVIDING_LINE);
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        final String goodbyeMessage = " Bye. Hope to see you again soon!\n";
        StringLogger.append(goodbyeMessage);
    }

    /**
     * Shows a given message.
     *
     * @param str The string to be shown.
     */
    public void showMessage(String str) {
        StringLogger.append(str);
    }

    /**
     * Shows the error message.
     *
     * @param errorMessage Error message to be shown.
     */
    public void showError(String errorMessage) {
        final String errorOpeningMessage = "You get an error: ";
        StringLogger.append(errorOpeningMessage + errorMessage + "\n");
    }

    /**
     * Shows loading error.
     */
    public void showLoadingError() {
        final String loadingErrorMessage = "Given tasklist cannot be loaded. Now creating a new tasklist instead.";
        showError(loadingErrorMessage);
    }

    public String readCommand() {
        return in.nextLine();
    }
}
