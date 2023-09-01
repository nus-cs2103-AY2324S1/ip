package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {
    public static final String DIVIDER = "__________________________________________________";
    public static final String INDENT = "    ";
    public static final String NAME = "Buzu";
    private static final String[] WELCOME_MESSAGE = {"Hello! I'm " + NAME + ".", "What can I do for you?"};
    private static final String[] GOODBYE_MESSAGE = {"Bye! Hope to see you again soon!"};
    private static final String[] INVALID_COMMAND_MESSAGE = {"Sorry, but I don't know what that means :("};
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
        showMessage(WELCOME_MESSAGE);
    }

    /**
     * Shows a goodbye message to the user, typically during termination of the program.
     */
    public void showGoodbyeMessage() {
        showMessage(GOODBYE_MESSAGE);
    }

    /**
     * Shows an invalid command message to the user.
     */
    public void showInvalidCommandMessage() {
        showMessage(INVALID_COMMAND_MESSAGE);
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
        return input;
    }
}
