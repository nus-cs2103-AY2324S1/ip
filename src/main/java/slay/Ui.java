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

    public Ui() {
    }

    /**
     * Generates the welcome message upon the start of the application.
     *
     * @return String of welcome message.
     */
    public static String getWelcomeMessage() {
        return DIVIDER + "\n" + Message.MESSAGE_WELCOME + "\n" + Message.MESSAGE_PROMPT + "\n" + DIVIDER;
    }

    /**
     * Shows the result of a command execution to the user.
     *
     * @param result Result of a command execution.
     * @return String that needs to be shown to the user.
     */
    public String showResultToUser(CommandResult result) {
        return result.feedbackToUser;
    }
}
