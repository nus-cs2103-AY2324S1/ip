package woof;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import command.Command;
import enums.ExceptionMessage;
import exceptions.WoofException;
import parser.Parser;
import storage.TaskFileHandler;
import tasks.TaskList;
import ui.Ui;

/**
 * The `Woof` class is the main class for the Woof CLI application.
 * It handles user interactions and the core functionality of the CLI application.
 */
public class Woof {
    /**
     * The main entry point for the Woof CLI application.
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
        System.out.println(Ui.getHelloWorldMessage());
        boolean isConversing = true;
        while (isConversing) {
            TaskList taskList = TaskFileHandler.readFromFile();

            System.out.println(Ui.getUserTitle());
            String rawCommand = Ui.getUserInput(scanner);

            System.out.println(Ui.getBotTitle());
            Command command = Parser.parse(rawCommand);
            System.out.print(command.execute(taskList));

            isConversing = !command.isByeCommand();
            TaskFileHandler.saveToFile(taskList);
        }
    }

    /**
     * Validates a date-time string using the application date-time formatter.
     *
     * @param string The date-time string to validate.
     */
    public static void validateDateTime(String string) {
        assert string != null : "datetime string cannot be null";

        try {
            LocalDate.parse(string, getDateTimeFormatter());
        } catch (DateTimeParseException e) {
            throw new WoofException(ExceptionMessage.INVALID_DATE_TIME_FORMAT.toFormattedString(string));
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
     * Adds line breaks with a separator to the text.
     */
    public static String wrapText(String message, String separator, int length) {
        assert message != null : "text cannot be null";
        assert separator != null : "seperator cannot be null";
        assert length > 0 : "length has to be more than 0";

        int currentLineLength = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            if (currentChar == '\n') {
                currentLineLength = 0;
            }
            if (currentLineLength >= length) {
                result.append(separator);
                currentLineLength = 0;
            }
            if (currentLineLength != 0 || currentChar != ' ') {
                result.append(currentChar);
                currentLineLength++;
            }

        }
        return result.toString();
    }

    /**
     * The getter for the datetime formatter used for parsing and formatting dates.
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }
}
