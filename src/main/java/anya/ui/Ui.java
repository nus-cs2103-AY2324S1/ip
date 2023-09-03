package anya.ui;

import static anya.messages.Messages.MESSAGE_ERROR_INIT;
import static anya.messages.Messages.MESSAGE_ERROR_LOADING;
import static anya.messages.Messages.MESSAGE_EXIT;
import static anya.messages.Messages.MESSAGE_GREETING;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents the User Interface (UI) of the Anya application.
 * The UI handles interactions with the user, displaying messages, and receiving user input.
 */
public class Ui {

    private static final String DIVIDER = "____________________________________________________________";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a new `Ui` instance using the default input and output streams (System.in and System.out).
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a new `Ui` instance with the specified input and output streams.
     *
     * @param in  The input stream to read user input from.
     * @param out The output stream to display messages to the user.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays a message to the user, surrounded by a divider for better readability.
     *
     * @param message The message to be shown.
     */
    public void showToUser(String message) {
        out.println(LS + DIVIDER + LS
                + message.replace("\n", LS)
                + LS + DIVIDER + LS);
    }

    /**
     * Generates and displays the welcome message when the application starts.
     */
    public void showGreetingMessage() {
        showToUser(MESSAGE_GREETING);
    }

    /**
     * Displays an error message when there is an issue loading data.
     */
    public void showLoadingError() {
        showToUser(MESSAGE_ERROR_LOADING);
    }

    /**
     * Displays a message to inform the user that the application is exiting.
     */
    public void showExitMessage() {
        showToUser(MESSAGE_EXIT);
    }

    /**
     * Displays a list of tasks to the user.
     *
     * @param list The list of tasks to be displayed.
     */
    public void showTaskList(String list) {
        showToUser(list);
    }

    /**
     * Displays an error message during the application initialization.
     */
    public void showInitError() {
        showToUser(MESSAGE_ERROR_INIT);
    }
}
