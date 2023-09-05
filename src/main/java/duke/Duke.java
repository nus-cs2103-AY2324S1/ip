package duke;

import command.Command;
import parser.Parser;
import storage.TaskFileHandler;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The `Duke` class is the main class for the Duke application.
 * It handles user interactions and the core functionality of the application.
 */
public class Duke {

    /**
     * The date-time formatter used for parsing and formatting dates.
     */
    public final static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * The main entry point for the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        runDuke();
    }

    /**
     * Runs the Duke application, allowing the user to interact with it via the command line.
     */
    public static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        Ui.helloWorld();
        boolean isConversing = true;
        while (isConversing) {
            String rawCommand = Ui.getUserInput(scanner);
            TaskList taskList = TaskFileHandler.readFromFile();
            Ui.getBotMessage();
            Command command = Parser.parse(rawCommand);
            command.execute(taskList);
            TaskFileHandler.saveToFile(taskList);
            isConversing = !command.isByeCommand();
        }
    }

    /**
     * Validates a date-time string using the duke date-time formatter.
     *
     * @param string The date-time string to validate.
     * @return `true` if the string is a valid date-time, `false` otherwise.
     */
    public static boolean validateDateTime(String string) {
        try {
            LocalDate.parse(string, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
