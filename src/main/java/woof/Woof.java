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
     * @return `true` if the string is a valid date-time, `false` otherwise.
     */
    public static boolean validateDateTime(String string) {
        try {
            LocalDate.parse(string, getDateTimeFormatter());
        } catch (DateTimeParseException e) {
            throw new WoofException(ExceptionMessage.INVALID_DATE_TIME_FORMAT.getValueFormat());
        }
        return true;
    }

    /**
     * The getter for the datetime formatter used for parsing and formatting dates.
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ISO_LOCAL_DATE;
    }
}
