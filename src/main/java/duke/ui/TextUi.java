package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static duke.common.Messages.MESSAGE_FIRST_PROMPT;
import static duke.common.Messages.MESSAGE_GOODBYE;
import static duke.common.Messages.MESSAGE_INVALID_COMMAND;
import static duke.common.Messages.MESSAGE_WELCOME;

/**
 * Responsible for showing and retrieving messages to and from the user.
 */
public class TextUi {
    public static final String DIVIDER = "__________________________________________________";
    public static final String INDENT = "    ";
    public static final String NAME = "Buzu";
    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Sends a formatted response to the user via standard input.
     *
     * @param text The text to respond with, line by line
     */
    public void showMessage(String... text) {
        out.printf("%s%s%n", INDENT, DIVIDER);
        for (String line : text) {
            out.printf("%s%s%n", INDENT, line);
        }
        out.printf("%s%s%n", INDENT, DIVIDER);
        out.println();
    }

    /**
     * Shows a welcome message to the user, typically during the start of program.
     */
    public void showWelcomeMessage() {
        showMessage(MESSAGE_WELCOME, MESSAGE_FIRST_PROMPT);
    }

    /**
     * Shows a goodbye message to the user, typically during termination of the program.
     */
    public void showGoodbyeMessage() {
        showMessage(MESSAGE_GOODBYE);
    }

    /**
     * Shows an invalid command message to the user.
     */
    public void showInvalidCommandMessage() {
        showMessage(MESSAGE_INVALID_COMMAND);
    }

    /**
     * Returns the input string from the user command.
     * Ignores empty or lines with only whitespace.
     *
     * @return the input string for the user command
     */
    public String getUserCommand() {
        String input;
        do {
            input = in.nextLine();
        } while (input.trim().isEmpty());
        return input.trim();
    }
}
