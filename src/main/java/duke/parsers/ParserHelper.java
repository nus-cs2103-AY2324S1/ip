package duke.parsers;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AddTaskCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.MarkAsDoneCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Helper class for parsing various types of commands and tasks.
 */
public class ParserHelper {
    private static final Pattern EMPTY_STRING_CHECKER = Pattern.compile("\\S.*+");
    private static final Pattern NUMBER_CHECKER = Pattern.compile("\\d+?");

    /**
     * Parses the information to create a MarkAsDoneCommand.
     *
     * @param info The information string.
     * @return A MarkAsDoneCommand object.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    public static MarkAsDoneCommand parseMarkCommand(String info) throws UnknownCommandException {
        return new MarkAsDoneCommand(parseNumericCommand(info));
    }

    /**
     * Parses the information to create an UnmarkCommand.
     *
     * @param info The information string.
     * @return An UnmarkCommand object.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    public static UnmarkCommand parseUnmarkCommand(String info) throws UnknownCommandException {
        return new UnmarkCommand(parseNumericCommand(info));
    }

    /**
     * Parses the information to create a DeleteCommand.
     *
     * @param info The information string.
     * @return A DeleteCommand object.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    public static DeleteCommand parseDeleteCommand(String info) throws UnknownCommandException {
        return new DeleteCommand(parseNumericCommand(info));
    }

    /**
     * Helper method for parsing numeric commands.
     *
     * @param info The information string.
     * @return An integer parsed from the string.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    private static int parseNumericCommand(String info) throws UnknownCommandException {
        if (NUMBER_CHECKER.matcher(info).matches()) {
            return Integer.parseInt(info) - 1;
        }
        throw new UnknownCommandException(ErrorMessages.INVALID_TASK_INDEX_ERROR);
    }

    /**
     * Parses the information to create a FindCommand.
     *
     * @param info The information string.
     * @return A FindCommand object.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    public static FindCommand parseFindCommand(String info) throws UnknownCommandException {
        checkNotEmpty(info, ErrorMessages.EMPTY_DESCRIPTION_ERROR);
        return new FindCommand(info.split(" "));
    }

    /**
     * Parses the information to create a HelpCommand.
     *
     * @param info The information string.
     * @return A HelpCommand object.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    public static HelpCommand parseHelpCommand(String info) throws UnknownCommandException {
        return new HelpCommand(EMPTY_STRING_CHECKER.matcher(info).matches() ? info : "normal");
    }

    /**
     * Parses the information to create an AddTaskCommand for Todo tasks.
     *
     * @param info The information string.
     * @return An AddTaskCommand object for Todo tasks.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    public static AddTaskCommand parseTodoCommand(String info) throws UnknownCommandException {
        checkNotEmpty(info, ErrorMessages.EMPTY_TODO_ERROR);
        return new AddTaskCommand(new Todo(info));
    }

    /**
     * Parses the information to create an AddTaskCommand for Deadline tasks.
     *
     * @param info The information string.
     * @return An AddTaskCommand object for Deadline tasks.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    public static AddTaskCommand parseDeadlineCommand(String info) throws UnknownCommandException {
        checkNotEmpty(info, ErrorMessages.EMPTY_DEADLINE_ERROR);
        return createDeadlineTaskCommand(extractFields(info, "(?<name>.*)/by\\s*(?<date>.*)"));
    }

    /**
     * Parses the information to create an AddTaskCommand for Event tasks.
     *
     * @param info The information string.
     * @return An AddTaskCommand object for Event tasks.
     * @throws UnknownCommandException if the command cannot be parsed.
     */
    public static AddTaskCommand parseEventCommand(String info) throws UnknownCommandException {
        checkNotEmpty(info, ErrorMessages.EMPTY_EVENT_ERROR);
        return createEventTaskCommand(extractFields(info, "(?<name>.*)/from(?<from>.*)/to(?<to>.*)"));
    }

    private static void checkNotEmpty(String info, String errorMsg) throws UnknownCommandException {
        if (!EMPTY_STRING_CHECKER.matcher(info).matches()) {
            throw new UnknownCommandException(errorMsg);
        }
    }

    private static Matcher extractFields(String info, String regex) {
        return Pattern.compile(regex).matcher(info);
    }

    private static AddTaskCommand createDeadlineTaskCommand(Matcher matcher) throws UnknownCommandException {
        if (!matcher.matches()) {
            throw new UnknownCommandException(ErrorMessages.INVALID_DEADLINE_FORMAT_ERROR);
        }
        String name = matcher.group("name").trim();
        String date = matcher.group("date").trim();
        try {
            return new AddTaskCommand(new Deadline(name, TimeParser.parseToLocalDateTime(date)));
        } catch (DateTimeParseException e) {
            throw new UnknownCommandException(ErrorMessages.INVALID_DATETIME_ERROR);
        }
    }

    private static AddTaskCommand createEventTaskCommand(Matcher matcher) throws UnknownCommandException {
        if (!matcher.matches()) {
            throw new UnknownCommandException(ErrorMessages.INVALID_EVENT_FORMAT_ERROR);
        }
        String name = matcher.group("name").trim();
        String from = matcher.group("from").trim();
        String to = matcher.group("to").trim();
        try {
            return new AddTaskCommand(new Event(name,
                    TimeParser.parseToLocalDateTime(from), TimeParser.parseToLocalDateTime(to)));
        } catch (DateTimeParseException e) {
            throw new UnknownCommandException(ErrorMessages.INVALID_DATETIME_ERROR);
        }
    }
}
