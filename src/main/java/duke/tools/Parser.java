package duke.tools;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import duke.commands.AddCommand;
import duke.commands.CheckCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.UnknownCommandException;

public class Parser {

    /**
     * Represents the different commands accepted by the chatbot
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
