package slay;

import slay.command.CommandResult;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * UI of the application.
 */
public class Ui {
    private static final String DIVIDER = "-----------------------------------";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String readCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public static String getWelcomeMessage() {
        return DIVIDER + "\n" + Message.MESSAGE_WELCOME + "\n" + Message.MESSAGE_PROMPT + "\n" + DIVIDER;
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public String showResultToUser(CommandResult result) {
        return result.feedbackToUser;
    }
}
