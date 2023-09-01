package shiba.ui;

import shiba.exceptions.ShibaException;

/**
 * Represents a class that prints messages to the user.
 */
public class Replier {
    private static final String HORZ_LINE = "____________________________________________________________";

    /**
     * Prints the greeting message.
     */
    public static void printGreeting(String botName) {
        printHorizontalLine();
        printWithLevel2Indent("Woof! I'm " + botName);
        printWithLevel2Indent("What can I bark at you?");
        printHorizontalLine();
    }

    /**
     * Prints the bye message
     */
    public static void printBye() {
        printHorizontalLine();
        printWithLevel2Indent("Woof! Hope to bark at you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints the unknown command message.
     */
    public static void printUnknownCommand() {
        printHorizontalLine();
        printWithLevel2Indent("Woof! I don't know what that command is!");
        printHorizontalLine();
    }

    /**
     * Prints the invalid command message.
     *
     * @param e The ShibaException whose message is to be printed.
     */
    public static void printException(ShibaException e) {
        printHorizontalLine();
        printWithLevel2Indent("Woof! " + e.getMessage());
        printHorizontalLine();
    }

    /**
     * Prints a horizontal line.
     */
    public static void printHorizontalLine() {
        printWithLevel1Indent(HORZ_LINE);
    }

    /**
     * Prints the given message with a single tab indent (4 spaces).
     *
     * @param message The message to be printed.
     */
    public static void printWithLevel1Indent(String message) {
        printWithIndents(message, 1, 0);
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
        System.out.println(" ".repeat(tabs * 4 + spaces) + message);
    }
}
