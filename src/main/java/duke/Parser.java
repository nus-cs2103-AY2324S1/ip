package duke;

import duke.command.*;
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
            "\\b(bye|list|unmark|mark|todo|deadline|event|delete)\\s*"  // match command
                    + "([^/]*[^/\\s])?\\s*"                             // match chars that are not / after command
                    + "(?:(/by|/from)\\s+([^/]*[^/\\s]))?\\s*"          // match /by or /from command and argument
                    + "(?:(/to)\\s+([^/]*[^/\\s]))?\\s*";               // match /to command and argument
    private static final Pattern pattern = Pattern.compile(regexPattern);

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
            throw new DukeException(Ui.LINE + Messages.INVALID_INPUT_MESSAGE + Ui.LINE);
        }

        int index;
        switch (matcher.group(1)) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (matcher.group(2) == null) {
                throw new DukeException(Ui.LINE + Messages.INVALID_MARK_MESSAGE + Ui.LINE);
            }
            index = Integer.parseInt(matcher.group(2));
            return new MarkCommand(index);
        case "unmark":
            if (matcher.group(2) == null) {
                throw new DukeException(Ui.LINE + Messages.INVALID_UNMARK_MESSAGE + Ui.LINE);
            }
            index = Integer.parseInt(matcher.group(2));
            return new UnmarkCommand(index);
        case "todo":
            if (matcher.group(2) == null) {
                throw new DukeException(Ui.LINE + Messages.INVALID_TODO_MESSAGE + Ui.LINE);
            }
            return new AddTaskCommand(new Todo(matcher.group(2)));
        case "deadline":
            if (matcher.group(2) == null
                    || matcher.group(3) == null
                    || !matcher.group(3).equals("/by")
                    || matcher.group(4) == null) {
                throw new DukeException(Ui.LINE + Messages.INVALID_DEADLINE_MESSAGE + Ui.LINE);
            }

            LocalDateTime parsedDate;
            try {
                parsedDate = LocalDateTime.parse(matcher.group(4),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new DukeException(Ui.LINE + Messages.INVALID_DEADLINE_MESSAGE + Ui.LINE);
            }

            return new AddTaskCommand(new Deadline(matcher.group(2), parsedDate));
        case "event":
            if (matcher.group(2) == null
                    || matcher.group(3) == null
                    || !matcher.group(3).equals("/from")
                    || matcher.group(4) == null
                    || matcher.group(5) == null
                    || !matcher.group(5).equals("/to")
                    || matcher.group(6) == null) {
                throw new DukeException(Ui.LINE + Messages.INVALID_EVENT_MESSAGE + Ui.LINE);
            }
            return new AddTaskCommand(new Event(matcher.group(2), matcher.group(4), matcher.group(6)));
        case "delete":
            if (matcher.group(2) == null) {
                throw new DukeException(Ui.LINE + Messages.INVALID_DELETE_MESSAGE + Ui.LINE);
            }
            index = Integer.parseInt(matcher.group(2));
            return new DeleteCommand(index);
        default:
            throw new DukeException(Ui.LINE + Messages.INVALID_INPUT_MESSAGE + Ui.LINE);
        }
    }
}
