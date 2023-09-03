package shiba.ui;

import javafx.application.Platform;
import shiba.exceptions.ShibaException;

/**
 * Represents a class that prints messages to the user.
 */
public class Replier {
    private static final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Prints the greeting message.
     */
    public static void printGreeting(String botName) {
        printWithLevel2Indent("Woof! I'm " + botName);
        printWithLevel2Indent("What can I bark at you?");
        reply();
    }

    /**
     * Prints the bye message
     */
    public static void printBye() {
        printWithLevel2Indent("Woof! Hope to bark at you again soon!");
        reply();
    }

    /**
     * Prints the unknown command message.
     */
    public static void printUnknownCommand() {
        printWithLevel2Indent("Woof! I don't know what that command is!");
        reply();
    }

    /**
     * Prints the invalid command message.
     *
     * @param e The ShibaException whose message is to be printed.
     */
    public static void printException(ShibaException e) {
        printWithLevel2Indent("Woof! " + e.getMessage());
        reply();
    }

    /**
     * Prints the given message with a single tab indent and 1 space.
     *
     * @param message The message to be printed.
     */
    public static void printWithLevel2Indent(String message) {
        printWithIndents(message, 1, 1);
    }

    /**
     * Prints the given message with a single tab indent and 2 spaces.
     *
     * @param message The message to be printed.
     */
    public static void printWithLevel3Indent(String message) {
        printWithIndents(message, 1, 3);
    }

    /**
     * Prints the given message with the given number of indents (spaces).
     *
     * @param message The message to be printed.
     * @param tabs The number of tab indents (4 spaces each).
     * @param spaces The number of spaces indents (in addition to tabs).
     */
    public static void printWithIndents(String message, int tabs, int spaces) {
        addToReply(" ".repeat(tabs * 4 + spaces) + message);
    }

    /**
     * Adds a new line to the reply message.
     *
     * @param message The message to be added to the string builder.
     */
    public static void addToReply(String message) {
        if (!stringBuilder.isEmpty()) {
            stringBuilder.append("\n");
        }
        stringBuilder.append(message);
    }

    /**
     * Sends the reply message stored in stringBuilder to the UI window to be displayed.
     */
    public static void reply() {
        MainWindow mainWindow = MainWindow.getInstance();
        if (mainWindow == null) {
            return;
        }

        String toPrint = stringBuilder.toString();
        stringBuilder.setLength(0);
        Platform.runLater(() -> mainWindow.addBotDialogNode(toPrint));
    }
}
