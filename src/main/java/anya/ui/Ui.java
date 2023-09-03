package anya.ui;

import static anya.messages.Messages.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


/**
 * UI of the application.
 * Deals with interactions with user.
 */
public class Ui {

    private static final String DIVIDER = "____________________________________________________________";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Shows message to the user .
     * @param message message to be shown.
     */
    public void showToUser(String message) {
        out.println(LS + DIVIDER + LS
                + message.replace("\n", LS)
                + LS + DIVIDER + LS);
    }

    /** Generates and prints the welcome message upon the start of the application. */
    public void showGreetingMessage() {
        showToUser(MESSAGE_GREETING);
    }

    public void showLoadingError() {
        showToUser(MESSAGE_ERROR_LOADING);
    }

    public void showExitMessage() {
        showToUser(MESSAGE_EXIT);
    }

    public void showTaskList(String list) {
        showToUser(list);
    }

    public void showInitError() {
        showToUser(MESSAGE_ERROR_INIT);
    }
}
