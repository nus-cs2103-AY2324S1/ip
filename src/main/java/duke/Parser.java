package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that parses the input strings.
 */
public class Parser {
    private static final String regexPattern =
            "\\b(bye|list|unmark|mark|todo|deadline|event|delete|find)\\s*"  // match command
                    + "([^/]*[^/\\s])?\\s*"                             // match chars that are not / after command
                    + "(?:(/by|/from)\\s+([^/]*[^/\\s]))?\\s*"          // match /by or /from command and argument
                    + "(?:(/to)\\s+([^/]*[^/\\s]))?\\s*";               // match /to command and argument
    private static final Pattern pattern = Pattern.compile(regexPattern);
    
    private static ListCommand parseListCommand(Matcher matcher) {
        boolean isArgumentEmpty = matcher.group(2) == null;

        if (isArgumentEmpty) {
            // don't sort if second argument empty
            return new ListCommand(false);
        }

        return new ListCommand(matcher.group(2).equals("sort"));
    }

    private static MarkCommand parseMarkCommand(Matcher matcher) throws DukeException {
        boolean isArgumentEmpty = matcher.group(2) == null;

        if (isArgumentEmpty) {
            throw new DukeException(Messages.MESSAGE_INVALID_MARK);
        }

        int index = Integer.parseInt(matcher.group(2));
        return new MarkCommand(index);
    }

    private static UnmarkCommand parseUnmarkCommand(Matcher matcher) throws DukeException {
        boolean isArgumentEmpty = matcher.group(2) == null;

        if (isArgumentEmpty) {
            throw new DukeException(Messages.MESSAGE_INVALID_UNMARK);
        }

        int index = Integer.parseInt(matcher.group(2));
        return new UnmarkCommand(index);
    }

    private static AddTaskCommand parseTodoCommand(Matcher matcher) throws DukeException {
        boolean isArgumentEmpty = matcher.group(2) == null;

        if (isArgumentEmpty) {
            throw new DukeException(Messages.MESSAGE_INVALID_TODO);
        }

        return new AddTaskCommand(new Todo(matcher.group(2)));
    }

    private static AddTaskCommand parseDeadlineCommand(Matcher matcher) throws DukeException {
        boolean isArgumentEmpty = matcher.group(2) == null
                || matcher.group(3) == null
                || matcher.group(4) == null;
        boolean isKeywordPresent = matcher.group(3).equals("/by");

        if (isArgumentEmpty || !isKeywordPresent) {
            throw new DukeException(Messages.MESSAGE_INVALID_DEADLINE);
        }

        LocalDateTime parsedDate;
        try {
            parsedDate = LocalDateTime.parse(matcher.group(4),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException(Messages.MESSAGE_INVALID_DEADLINE);
        }

        return new AddTaskCommand(new Deadline(matcher.group(2), parsedDate));
    }

    private static AddTaskCommand parseEventCommand(Matcher matcher) throws DukeException {
        boolean isArgumentEmpty = matcher.group(2) == null
                || matcher.group(3) == null
                || matcher.group(4) == null
                || matcher.group(5) == null
                || matcher.group(6) == null;
        boolean isKeywordPresent = matcher.group(3).equals("/from")
                && matcher.group(5).equals("/to");

        if (isArgumentEmpty || !isKeywordPresent) {
            throw new DukeException(Messages.MESSAGE_INVALID_EVENT);
        }

        return new AddTaskCommand(new Event(matcher.group(2), matcher.group(4), matcher.group(6)));
    }

    private static DeleteCommand parseDeleteCommand(Matcher matcher) throws DukeException {
        boolean isArgumentEmpty = matcher.group(2) == null;

        if (isArgumentEmpty) {
            throw new DukeException(Messages.MESSAGE_INVALID_DELETE);
        }

        int index = Integer.parseInt(matcher.group(2));
        return new DeleteCommand(index);
    }

    private static FindCommand parseFindCommand(Matcher matcher) throws DukeException {
        boolean isArgumentEmpty = matcher.group(2) == null;

        if (isArgumentEmpty) {
            throw new DukeException(Messages.MESSAGE_INVALID_FIND);
        }

        return new FindCommand(matcher.group(2));
    }

    /**
     * Parses the provided input string and returns the corresponding duke.command.Command instance.
     *
     * @param command The command string.
     * @return The Command instance corresponding to the parsed input.
     * @throws DukeException if the input command is not valid.
     */
    public static Command parse(String command) throws DukeException {
        Matcher matcher = pattern.matcher(command);

        if (!matcher.matches()) {
            // no match means input is not valid
            throw new DukeException(Messages.MESSAGE_INVALID_INPUT);
        }

        switch (matcher.group(1)) {
        case "bye":
            return new ExitCommand();
        case "list":
            return parseListCommand(matcher);
        case "mark":
            return parseMarkCommand(matcher);
        case "unmark":
            return parseUnmarkCommand(matcher);
        case "todo":
            return parseTodoCommand(matcher);
        case "deadline":
            return parseDeadlineCommand(matcher);
        case "event":
            return parseEventCommand(matcher);
        case "delete":
            return parseDeleteCommand(matcher);
        case "find":
            return parseFindCommand(matcher);
        default:
            throw new DukeException(Messages.MESSAGE_INVALID_INPUT);
        }
    }
}
