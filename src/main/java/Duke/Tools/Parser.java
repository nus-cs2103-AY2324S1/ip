package Duke.Tools;

import Duke.Commands.*;
import Duke.Exceptions.UnknownCommandException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Handles the parsing of user commands and making sense of the user command.
 */
public class Parser {

    /**
     * Represents the different commands accepted by the chatbot.
     */
    public enum Operations {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, CHECK,
    }

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .optionalStart().appendPattern(" HH:mm").optionalEnd()
            .optionalStart().appendPattern(" HHmm").optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    /**
     * Parses the full command and returns the corresponding Command object.
     *
     * @param fullCommand The full command string provided by the user.
     * @return A Command object representing the parsed command.
     * @throws UnknownCommandException If the command is not recognized.
     */
    public static Command parse(String fullCommand) throws UnknownCommandException {
        // Use Regex to extract the first word even with preceding whitespace
        Operations command = Operations.valueOf(fullCommand.replaceAll("^\\W*\\b(\\w+).*", "$1").toUpperCase());

        switch (command) {
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(fullCommand, FORMATTER);
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(fullCommand);
        case UNMARK:
            return new UnmarkCommand(fullCommand);
        case DELETE:
            return new DeleteCommand(fullCommand);
        case CHECK:
            return new CheckCommand(fullCommand, FORMATTER);
        default:
            throw new UnknownCommandException(fullCommand);
        }
    }
}
