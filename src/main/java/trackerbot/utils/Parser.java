package trackerbot.utils;

import java.util.Scanner;

import trackerbot.command.Command;
import trackerbot.command.CommandType;
import trackerbot.exception.TrackerBotException;
import trackerbot.task.Deadline;
import trackerbot.task.Event;
import trackerbot.task.Task;
import trackerbot.task.Todo;

/**
 * Contains static methods to parse user input.
 *
 * @author WZWren
 * @version A-CodeQuality
 */
public class Parser {
    private static final String MASS_COMMAND_DELIMITER = ";";

    /**
     * Splits the user input into its keyword and commandField components.
     * <p>Parser does not directly handle the Command logic of the input. After
     * splitting the user input, it passes it into Command.of method to identify
     * the Command and return it.</p>
     *
     * @param input The unmodified console string that the user inputs.
     * @return An appropriate command corresponding to the user input.
     */
    public static Command parseCommand(String input) {
        Scanner scanner = new Scanner(input);
        String keyword;
        String rest;

        if (!scanner.hasNext()) {
            keyword = "";
        } else {
            keyword = scanner.next();
        }

        if (keyword.equals("") || !scanner.hasNextLine()) {
            rest = "";
        } else {
            rest = scanner.nextLine().trim();
        }

        return Command.of(CommandType.getCommandType(keyword), rest);
    }

    /**
     * Splits the command field of mass operation commands into its nested fields.
     *
     * @param commandField The description of the Commands, separated by MASS_COMMAND_DELIMITER.
     * @return An array of Strings corresponding to a command input.
     */
    public static String[] parseMassOpFields(String commandField) {
        return commandField.split(MASS_COMMAND_DELIMITER);
    }

    /**
     * Parses the user input arising from the add keyword.
     * <p>This method further parses the user input during the add command,
     * to differentiate between the Task types.</p>
     *
     * @param type The enumerated type of Command to add into the Task.
     * @param commandField The description of the Command.
     * @return A corresponding subclass of Task.
     * @throws TrackerBotException if the user input is in an invalid format.
     */
    public static Task parseAdd(CommandType type, String commandField) throws TrackerBotException {
        Task newTask;
        switch (type) {
        case TODO:
            newTask = createTodo(commandField);
            break;
        case DEADLINE:
            newTask = createDeadline(commandField);
            break;
        case EVENT:
            newTask = createEvent(commandField);
            break;
        default:
            throw new IllegalStateException("Uncaught CommandType: " + type.getKeyword());
        }
        return newTask;
    }

    private static Task createTodo(String commandField) throws TrackerBotException {
        if (commandField.equals("")) {
            throw new TrackerBotException("Cannot track task without description.");
        }

        return new Todo(commandField.trim());
    }

    private static Task createDeadline(String commandField) throws TrackerBotException {
        final String flag = "/by";
        final String format = "^.+ /by .+";
        String[] segments;

        if (!commandField.matches(format)) {
            throw new TrackerBotException("Improper format: deadline [description] /by [end-date]");
        }

        segments = commandField.split(flag);
        if (segments.length > 2) {
            throw new TrackerBotException("Too many flags: deadline [description] /by [end-date]");
        }

        if (segments[0].trim().equals("")) {
            throw new TrackerBotException("Cannot track deadline without description.");
        }
        if (segments[1].trim().equals("")) {
            throw new TrackerBotException("Empty /by flag.");
        }

        return new Deadline(segments[0].trim(), segments[1].trim());
    }

    private static Task createEvent(String commandField) throws TrackerBotException {
        final String flag = "/from|/to";
        final String format = "^.+ /from .+ /to .+";
        String[] segments;

        if (!commandField.matches(format)) {
            throw new TrackerBotException(
                    "Improper format: event [description] /from [start-date] /to [end-date]");
        }

        segments = commandField.split(flag);
        if (segments.length > 3) {
            throw new TrackerBotException(
                    "Too many flags: event [description] /from [start-date] /to [end-date]");
        }

        if (segments[0].trim().equals("")) {
            throw new TrackerBotException("Cannot track task without description");
        }
        if (segments[1].trim().equals("")) {
            throw new TrackerBotException("Empty /from flag.");
        }
        if (segments[2].trim().equals("")) {
            throw new TrackerBotException("Empty /to flag.");
        }

        return new Event(segments[0].trim(), segments[1].trim(), segments[2].trim());
    }
}
