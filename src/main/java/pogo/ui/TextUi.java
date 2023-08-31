package pogo.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import pogo.commands.CommandResult;
import pogo.common.Messages;

/**
 * Represents the text user interface for the application.
 * Based on AddressBook-Level2.
 */
public class TextUi {
    private static final String HORIZONTAL_DIVIDER = "-".repeat(40);
    private final Scanner in;
    private final PrintStream out;

    /**
     * Creates a TextUi object with System.in and System.out and input and output.
     */
    public TextUi() {
        this(System.in, System.out);
    }

    /**
     * Creates a TextUi object with the specified input and output streams.
     *
     * @param in  The input stream.
     * @param out The output stream.
     */
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Shows message(s) to the user
     *
     * @param message Message(s) to show to the user
     */
    public void showToUser(String... message) {
        for (String m : message) {
            this.out.println(m);
        }
    }

    /**
     * Generates and prints the welcome message to the user.
     */
    public void showStartupMessage() {
        showToUser(Messages.STARTUP_MESSAGE, HORIZONTAL_DIVIDER);
    }

    /**
     * Generates and prints the initialisation success message to the user.
     *
     * @param taskCount Number of tasks loaded
     */
    public void showInitSuccessMessage(int taskCount) {
        showToUser(String.format(Messages.TASK_LOAD_SUCCESS, taskCount), HORIZONTAL_DIVIDER);
    }

    /**
     * Generates and prints the initialisation failure message to the user.
     */
    public void showInitFailureMessage() {
        showToUser(Messages.TASK_LOAD_FAILURE, HORIZONTAL_DIVIDER);
    }

    /**
     * Prompts for the user's input and reads the text entered by the user.
     * Ignores empty lines.
     *
     * @return Text entered by the user
     */
    public String getUserInput() {
        String line = in.nextLine();
        while (line.trim().isEmpty()) {
            line = in.nextLine();
        }
        showToUser(HORIZONTAL_DIVIDER);
        return line;
    }

    public void showCommandResultToUser(CommandResult cr) {
        showToUser(cr.getFeedbackToUser(), HORIZONTAL_DIVIDER);
    }
}
