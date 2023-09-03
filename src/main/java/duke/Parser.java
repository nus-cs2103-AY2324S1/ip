package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.CheckCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodayCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidFormatException;
import duke.exception.UnknownCommandException;


/**
 * Parser for user input.
 */
public class Parser {

    /**
     * Parses the input and creates the corresponding Command object.
     *
     * @param input The user input.
     * @return The Command object based on user input.
     * @throws DukeException If there is an issue parsing the input or creating the Command.
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toUpperCase();
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(Integer.parseInt(parseArgument(parts)) - 1);
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(parseArgument(parts)) - 1);
        case TODO:
            return new AddTodoCommand(parseArgument(parts));
        case DEADLINE:
            return parseAddDeadlineCommand(parseArgument(parts));
        case EVENT:
            return parseAddEventCommand(parseArgument(parts));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(parseArgument(parts)) - 1);
        case FIND:
            return new FindCommand(parseArgument(parts));
        case CHECK:
            return new CheckCommand(Parser.parseArgument(parts));
        case TODAY:
            return new TodayCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the argument from a user input and returns it.
     *
     * @param parts The split user input.
     * @return The argument portion of the user input.
     */
    public static String parseArgument(String[] parts) {
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }

    /**
     * Parses the argument to create an AddDeadlineCommand.
     *
     * @param argument The argument portion of the user input.
     * @return The AddDeadlineCommand based on the argument.
     * @throws InvalidFormatException If there is an issue with the argument format.
     */
    private static AddDeadlineCommand parseAddDeadlineCommand(String argument) throws InvalidFormatException {
        try {
            String[] deadlineParts = argument.split("/by");

            if (deadlineParts.length < 2) {
                throw new InvalidFormatException("Please use the format: deadline <description> /by <d/M/yyyy HHmm>.");
            }

            String description = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();

            LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new AddDeadlineCommand(description, dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date and time format. Please use the format: d/M/yyyy HHmm.");
        }
    }

    /**
     * Parses the argument to create an AddEventCommand.
     *
     * @param argument The argument portion of the user input.
     * @return The AddEventCommand based on the argument.
     * @throws InvalidFormatException If there is an issue with the argument format.
     */
    private static AddEventCommand parseAddEventCommand(String argument) throws InvalidFormatException {
        try {
            String[] eventParts = argument.split("/at");

            if (eventParts.length < 2) {
                throw new InvalidFormatException("Please use the format: event <description> /at <d/M/yyyy HHmm>");
            }

            String description = eventParts[0].trim();
            String at = eventParts[1].trim();

            LocalDateTime dateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new AddEventCommand(description, dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date and time format. Please use the format: d/M/yyyy HHmm.");
        }

    }
}
