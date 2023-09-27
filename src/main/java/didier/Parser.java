package didier;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
    public static Command parseCommand(String commandString) throws DidierException {
        String[] options = commandString.split(" ", 2);
        CommandType commandType = CommandType.textToCommand(options[0]);
        switch (commandType) {
        case LIST:
            return parseListCommand();
        case MARK:
            return parseMarkCommand(options);
        case UNMARK:
            return parseUnmarkCommand(options);
        case DELETE:
            return parseDeleteCommand(options);
        case TODO:
            return parseTodoCommand(options);
        case DEADLINE:
            return parseDeadlineCommand(options);
        case EVENT:
            return parseEventCommand(options);
        case FIND:
            return parseFindCommand(options);
        case BYE:
            return parseExitCommand();
        default:
            throw new InvalidCommandException(options[0]);
        }
    }

    private static ListCommand parseListCommand() {
        return new ListCommand();
    }

    private static ExitCommand parseExitCommand() {
        return new ExitCommand();
    }

    private static FindCommand parseFindCommand(String[] options) throws ElementMissingException {
        boolean isMissingKeyword = options.length == 1 || options[1].isBlank();
        if (isMissingKeyword) {
            throw new ElementMissingException("keyword");
        }
        return new FindCommand(options[1]);
    }

    private static AddCommand parseTodoCommand(String[] options) throws ElementMissingException, DateFormatException {
        return new AddCommand(getTaskDescription(options), null, null, null);
    }
    private static AddCommand parseDeadlineCommand(String[] options)
            throws ElementMissingException, DateFormatException {
        String[] deadlineCommand = options[1].split("\\\\by ", 2);
        return new AddCommand(getTaskDescription(options), getDeadlineBy(options), null, null);
    }
    private static AddCommand parseEventCommand(String[] options) throws ElementMissingException, DateFormatException {
        String description = getTaskDescription(options);
        LocalDate[] eventTimes = getEventTimes(options);
        assert eventTimes.length == 2 : "There are too many or too few event times, there should only be a from and to";
        LocalDate from = eventTimes[0];
        LocalDate to = eventTimes[1];
        return new AddCommand(getTaskDescription(options), null, from, to);
    }

    private static String getTaskDescription(String[] options) throws ElementMissingException {
        boolean isMissingDescription = options.length == 1 || options[1].isBlank();
        if (isMissingDescription) {
            throw new ElementMissingException("description");
        }
        return options[1].split("\\\\", 2)[0];
    }

    private static LocalDate getDeadlineBy(String[] options) throws ElementMissingException, DateFormatException {
        String[] deadlineCommand = options[1].split("\\\\by ", 2);
        if (deadlineCommand.length == 1 || deadlineCommand[1].isBlank()) {
            throw new ElementMissingException("deadline");
        }
        try {
            return LocalDate.parse(deadlineCommand[1].trim());
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    private static LocalDate[] getEventTimes(String[] options) throws ElementMissingException, DateFormatException {
        String[] fromCommand = options[1].split("\\\\from ", 2);
        if (fromCommand.length == 1 || fromCommand[1].isBlank()) {
            throw new ElementMissingException("start date");
        }
        String[] toCommand = fromCommand[1].split("\\\\to ", 2);
        if (toCommand.length == 1 || toCommand[1].isBlank()) {
            throw new ElementMissingException("end date");
        }
        try {
            return new LocalDate[]{LocalDate.parse(toCommand[0].trim()), LocalDate.parse(toCommand[1].trim())};
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    private static DeleteCommand parseDeleteCommand(String[] options)
            throws TaskNumberException, ElementMissingException {
        if (options.length == 1) {
            throw new ElementMissingException("task number");
        }
        try {
            int taskNumber = Integer.parseInt(options[1]);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new TaskNumberException(options[1]);
        }
    }

    private static MarkCommand parseMarkCommand(String[] options) throws TaskNumberException, ElementMissingException {
        return new MarkCommand(true, getMarkTaskNumber(options));
    }

    private static MarkCommand parseUnmarkCommand(String[] options)
            throws TaskNumberException, ElementMissingException {
        return new MarkCommand(false, getMarkTaskNumber(options));
    }

    private static int getMarkTaskNumber(String[] options) throws TaskNumberException, ElementMissingException {
        if (options.length == 1) {
            throw new ElementMissingException("task number");
        }
        try {
            int taskNumber = Integer.parseInt(options[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new TaskNumberException(options[1]);
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
