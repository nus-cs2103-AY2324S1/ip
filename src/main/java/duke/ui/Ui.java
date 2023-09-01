package duke.ui;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The Ui class handles user interface interactions and messages for the Duke application.
 * It provides methods for reading user commands, displaying welcome and goodbye messages,
 * and formatting messages with separators.
 */
public class Ui {
    /** The line separator used for separating messages in the output. */
    public static final String LINE_SEPARATOR = "____________________________________________________________";

    /** The date and time input format used for parsing user input. */
    public static final DateTimeFormatter DATE_FORMAT_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /** The date and time output format used for displaying dates and times in messages. */
    public static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");

    /** The scanner used for reading user input. */
    Scanner scanner;

    /**
     * Constructs a Ui object with a new scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a user command from the console.
     *
     * @return The user command as a string.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays a welcome message when the Duke application is started.
     */
    public void showWelcome() {
        System.out.println(messageWithSeparator("Hello! I'm David\nWhat can I do for you?"));
    }

    /**
     * Displays a goodbye message when the Duke application is exited.
     */
    public void showGoodbye() {
        System.out.println(messageWithSeparator("Bye. Hope to see you again soon!"));
    }

    /**
     * Formats a message by adding a line separator above and below it.
     *
     * @param message The message to be formatted.
     * @return The formatted message with separators.
     */
    public static String messageWithSeparator(String message) {
        return LINE_SEPARATOR+ "\n" + message + "\n" + LINE_SEPARATOR;
    }

    /**
     * Prints a message with separators above and below it.
     *
     * @param message The message to be printed.
     */
    public static void printMessageWithSeparator(String message) {
        System.out.println(messageWithSeparator(message));
    }
}
