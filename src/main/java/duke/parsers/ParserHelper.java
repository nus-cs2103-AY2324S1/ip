package duke.parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.MarkAsDoneCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class ParserHelper {
    private static final Pattern EMPTY_STRING_CHECKER = Pattern.compile("\\S.*+");
    private static final Pattern NUMBER_CHECKER = Pattern.compile("\\d+?");

    public static Command parseCommandByType(CommandType commandType, String information)
            throws UnknownCommandException {
        if (!NUMBER_CHECKER.matcher(information).matches()) {
            throw new UnknownCommandException(ErrorMessages.INVALID_TASK_INDEX_ERROR);
        }

        int index = Integer.parseInt(information) - 1;
        switch (commandType) {
        case MARK:
            return new MarkAsDoneCommand(index);
        case UNMARK:
            return new UnmarkCommand(index);
        case DELETE:
            return new DeleteCommand(index);
        default:
            throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
    }

    public static FindCommand parseFindCommand(String information) throws UnknownCommandException {
        validateNonEmptyInput(information, ErrorMessages.EMPTY_DESCRIPTION_ERROR);

        String[] descriptions = information.split(" ");
        return new FindCommand(descriptions);
    }

    public static AddTaskCommand parseTodoCommand(String information) throws UnknownCommandException {
        validateNonEmptyInput(information, ErrorMessages.EMPTY_TODO_ERROR);
        return new AddTaskCommand(new Todo(information));
    }

    public static AddTaskCommand parseDeadlineCommand(String information) throws UnknownCommandException {
        Matcher matcher = validateAndGetMatcher(information, "(?<name>.*)/by\\s*(?<date>.*)",
                ErrorMessages.EMPTY_DEADLINE_ERROR, ErrorMessages.INVALID_DEADLINE_FORMAT_ERROR);

        return createDeadlineTaskCommand(matcher);
    }

    public static AddTaskCommand parseEventCommand(String information) throws UnknownCommandException {
        Matcher matcher = validateAndGetMatcher(information, "(?<name>.*)/from(?<from>.*)/to(?<to>.*)",
                ErrorMessages.EMPTY_EVENT_ERROR, ErrorMessages.INVALID_EVENT_FORMAT_ERROR);

        return createEventTaskCommand(matcher);
    }

    private static void validateNonEmptyInput(String information, String errorMessage) throws UnknownCommandException {
        if (!EMPTY_STRING_CHECKER.matcher(information).matches()) {
            throw new UnknownCommandException(errorMessage);
        }
    }

    private static Matcher validateAndGetMatcher(String information, String pattern, String emptyError,
                                                 String formatError) throws UnknownCommandException {
        validateNonEmptyInput(information, emptyError);
        Matcher matcher = Pattern.compile(pattern).matcher(information);

        if (!matcher.matches()) {
            throw new UnknownCommandException(formatError);
        }

        return matcher;
    }

    private static AddTaskCommand createDeadlineTaskCommand(Matcher dateChecker) throws UnknownCommandException {
        return createTaskWithDateTime(dateChecker, "name", "date", Deadline::new);
    }

    private static AddTaskCommand createEventTaskCommand(Matcher intervalChecker) throws UnknownCommandException {
        String name = intervalChecker.group("name").trim();
        LocalDateTime from = parseDateTime(intervalChecker.group("from").trim());
        LocalDateTime to = parseDateTime(intervalChecker.group("to").trim());

        return new AddTaskCommand(new Event(name, from, to));
    }

    private static LocalDateTime parseDateTime(String dateTime) throws UnknownCommandException {
        try {
            return TimeParser.parseToLocalDateTime(dateTime);
        } catch (DateTimeParseException e) {
            throw new UnknownCommandException(ErrorMessages.INVALID_DATETIME_ERROR);
        }
    }

    private static AddTaskCommand createTaskWithDateTime(Matcher matcher, String nameGroup, String dateTimeGroup,
                                                         BiFunction<String, LocalDateTime, Task> constructor)
            throws UnknownCommandException {
        String name = matcher.group(nameGroup).trim();
        LocalDateTime dateTime = parseDateTime(matcher.group(dateTimeGroup).trim());

        Task task = constructor.apply(name, dateTime);
        return new AddTaskCommand(task);
    }
}
