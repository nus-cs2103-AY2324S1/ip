package didier;


import didier.command.AddCommand;
import didier.command.Command;
import didier.command.DeleteCommand;
import didier.command.ExitCommand;
import didier.command.FindCommand;
import didier.command.ListCommand;
import didier.command.MarkCommand;
import didier.exception.DateFormatException;
import didier.exception.DidierException;
import didier.exception.ElementMissingException;
import didier.exception.InvalidCommandException;
import didier.exception.TaskNumberException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Deals with making sense of all user input to the bot.
 */
public class Parser {

    /**
     * Returns a Command object which encapsulates the command String that is entered by the user.
     * Converts the user input to this Command object.
     *
     * @param commandString The command provided by the user.
     * @return The corresponding Command object.
     * @throws DidierException If the user command is invalid in any way.
     */
    public static Command parse(String commandString) throws DidierException {
        String[] options = commandString.split(" ", 2);
        CommandType commandType = CommandType.textToCommand(options[0]);
        switch (commandType) {
        case LIST:
            return new ListCommand();
        case MARK:
        case UNMARK:
            try {
                return new MarkCommand(commandType.equals(CommandType.MARK), Integer.parseInt(options[1]));
            } catch (NumberFormatException e) {
                throw new TaskNumberException(options[1]);
            }
        case DELETE:
            try {
                return new DeleteCommand(Integer.parseInt(options[1]));
            } catch (NumberFormatException e) {
                throw new TaskNumberException(options[1]);
            }
        case TODO:
        case DEADLINE:
        case EVENT:
            if (options.length == 1 || options[1].isBlank()) {
                throw new ElementMissingException("description");
            }
            if (options[0].equals("todo")) {
                return new AddCommand(options[1], null, null, null);
            } else if (options[0].equals("deadline")) {
                String[] deadlineCommand = options[1].split("\\\\by ", 2);
                if (deadlineCommand.length == 1 || deadlineCommand[1].isBlank()) {
                    throw new ElementMissingException("deadline");
                } else {
                    try {
                        return new AddCommand(deadlineCommand[0], LocalDate.parse(deadlineCommand[1].trim()),
                                null, null);
                    } catch (DateTimeParseException e) {
                        throw new DateFormatException();
                    }
                }
            } else {
                String[] fromCommand = options[1].split("\\\\from ", 2);
                if (fromCommand.length == 1 || fromCommand[1].isBlank()) {
                    throw new ElementMissingException("start date");
                }
                String[] toCommand = fromCommand[1].split("\\\\to ", 2);
                if (toCommand.length == 1 || toCommand[1].isBlank()) {
                    throw new ElementMissingException("end date");
                } else {
                    try {
                        return new AddCommand(fromCommand[0], null, LocalDate.parse(toCommand[0].trim()),
                                LocalDate.parse(toCommand[1].trim()));
                    } catch (DateTimeParseException e) {
                        throw new DateFormatException();
                    }
                }
            }
        case FIND:
            if (options.length == 1 || options[1].isBlank()) {
                throw new ElementMissingException("keyword");
            }
            return new FindCommand(options[1]);
        case BYE:
            return new ExitCommand();
        default:
            throw new InvalidCommandException(options[0]);
        }
    }

    enum CommandType {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        BYE,
        UNKNOWN;

        public static CommandType textToCommand(String string) {
            try {
                return CommandType.valueOf(string.toUpperCase());
            } catch (IllegalArgumentException e) {
                return CommandType.UNKNOWN;
            }
        }
    }

}
