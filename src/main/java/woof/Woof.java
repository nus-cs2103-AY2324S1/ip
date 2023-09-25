package woof;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import command.Command;
import command.NullCommand;
import enums.ExceptionMessage;
import exceptions.WoofException;
import parser.Parser;
import storage.TaskFileHandler;
import tasks.TaskList;
import ui.Ui;

/**
 * The `Woof` class is the main class for the Woof CLI application.
 * It handles user interactions and the core functionality of the application.
 */
public class Woof {
    private static final int CHAT_WIDTH = 60;

    /**
     * The main entry point for the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        runWoofCli();
    }

    /**
     * Runs the Woof CLI application, allowing the user to interact with it via the command line.
     */
    public static void runWoofCli() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(Ui.getHelloWorldMessage());
        converse(scanner);
        scanner.close();
    }

    /**
     * Handles the conversation loop of the Woof CLI application.
     *
     * @param scanner The Scanner object for user input.
     */
    public static void converse(Scanner scanner) {
        Command command = new NullCommand("");
        while (!command.isByeCommand()) {
            TaskList taskList = TaskFileHandler.readFromFile();

            System.out.println(Ui.getUserTitle());
            String rawCommand = Ui.getUserInput(scanner);

            System.out.println(Ui.getBotTitle());
            command = Parser.parse(rawCommand);
            String response = command.execute(taskList);
            String wrappedResponse = wrapText(response, "" , getChatWidth());
            System.out.println(wrappedResponse);

            TaskFileHandler.saveToFile(taskList);
        }
    }

    /**
     * Validates input date-time string using the application date-time formatter.
     *
     * @param string The date-time string to validate.
     */
    public static void validateInDateTime(String string) {
        assert string != null : "datetime string cannot be null";

        try {
            parseDateTimeIn(string);
        } catch (DateTimeParseException e) {
            throw new WoofException(ExceptionMessage.INVALID_DATE_TIME_FORMAT.toFormattedValue(string));
        }
    }

    /**
     * Updates the task list by executing a command, saving the file, and returning the result.
     *
     * @param command The command to be executed.
     * @return The result message of executing the command.
     */
    public static String updateFileAndExecute(Command command) {
        assert command != null : "command cannot be null";

        TaskList taskList = TaskFileHandler.readFromFile();
        String result = command.execute(taskList);
        TaskFileHandler.saveToFile(taskList);
        return result;
    }

    /**
     * Adds a separator every specified length to a text, wrapping it.
     *
     * @param message   The text to be wrapped.
     * @param separator The separator to be inserted when the text wraps.
     * @param length    The maximum length of each line before wrapping.
     * @return The wrapped text with separators.
     */
    public static String wrapText(String message, String separator, int length) {
        assert message != null : "text cannot be null";
        assert separator != null : "separator cannot be null";
        assert length > 0 : "length has to be more than 0";

        int currentLineLength = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (currentChar == '\n') {
                result.append(currentChar);
                result.append(separator);
                currentLineLength = -1;
            } else if (currentLineLength >= length) {
                result.append('\n');
                result.append(separator);
                result.append(currentChar);
                currentLineLength = 0;
            } else {
                result.append(currentChar);
            }
            currentLineLength++;
        }
        return result.toString();
    }

    /**
     * Provides the DateTimeFormatter for parsing and formatting input dates.
     *
     * @return The DateTimeFormatter for input date strings in ISO_LOCAL_DATE format.
     */
    public static DateTimeFormatter getDateTimeInFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }

    /**
     * Provides the DateTimeFormatter for formatting dates to be presented to the user.
     *
     * @return The DateTimeFormatter for output date strings in "dd MMM yyyy" format.
     */
    public static DateTimeFormatter getDateTimeOutFormatter() {
        return DateTimeFormatter.ofPattern("dd MMM yyyy");
    }

    /**
     * Parses a date string received by the application using the input formatter.
     *
     * @param dateString The date string to be parsed.
     * @return The parsed LocalDate.
     * @throws WoofException If the date string is invalid and cannot be parsed.
     */
    public static LocalDate parseDateTimeIn(String dateString) {
        assert dateString != null : "Date string cannot be null";

        try {
            return LocalDate.parse(dateString, getDateTimeInFormatter());
        } catch (DateTimeParseException e) {
            throw new WoofException(ExceptionMessage.INVALID_DATE_TIME_FORMAT.toFormattedValue(dateString));
        }
    }

    /**
     * Get the chat width for chat formatting.
     */
    public static int getChatWidth() {
        return Woof.CHAT_WIDTH;
    }

    /**
     * Formats a LocalDate into a user-friendly date string for presentation.
     *
     * @param date The LocalDate to format.
     * @return A formatted date string.
     * @throws IllegalArgumentException If the input LocalDate is null.
     */
    public static String parseDateTimeOut(LocalDate date) {
        assert date != null : "Date cannot be null";

        return date.format(getDateTimeOutFormatter());
    }
}
