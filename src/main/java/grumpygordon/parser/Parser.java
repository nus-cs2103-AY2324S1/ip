package grumpygordon.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import grumpygordon.commands.ByeCommand;
import grumpygordon.commands.Command;
import grumpygordon.commands.DeadlineCommand;
import grumpygordon.commands.DeleteCommand;
import grumpygordon.commands.EventCommand;
import grumpygordon.commands.FindCommand;
import grumpygordon.commands.ListCommand;
import grumpygordon.commands.MarkCommand;
import grumpygordon.commands.SortCommand;
import grumpygordon.commands.TodoCommand;
import grumpygordon.commands.UnmarkCommand;
import grumpygordon.exceptions.GrumpyGordonDateTimeFormatException;
import grumpygordon.exceptions.GrumpyGordonException;
import grumpygordon.exceptions.GrumpyGordonInitialisationException;
import grumpygordon.exceptions.GrumpyGordonInvalidCommandException;
import grumpygordon.tasks.Deadline;
import grumpygordon.tasks.Event;
import grumpygordon.tasks.Task;
import grumpygordon.tasks.TaskList;
import grumpygordon.tasks.Todo;

/**
 * Represents a parser that parses user input.
 */
public class Parser {
    /**
     * Separator for saved format.
     */
    private static final String SAVED_FORMAT_SEPARATOR_REGEX = " \\| ";
    /**
     * Separator for user input.
     */
    private static final String SPACE_REGEX = " ";
    /**
     * Regex for mark command.
     */
    private static final String MARK_REGEX = "mark\\s([0-9]+)$";
    /**
     * Regex for unmark command.
     */
    private static final String UNMARK_REGEX = "unmark\\s([0-9]+)$";
    /**
     * Regex for delete command.
     */
    private static final String DELETE_REGEX = "delete\\s([0-9]+)$";
    /**
     * Regex for find command.
     */
    private static final String FIND_REGEX = "find\\s.*$";
    /**
     * Regex for deadline command.
     */
    private static final String DEADLINE_INFO_REGEX = ".*\\s+/by\\s+.*";
    /**
     * Regex for event command.
     */
    private static final String EVENT_INFO_REGEX = ".*\\s+/from\\s+.*\\s+/to\\s+.*";
    /**
     * Regex for datetime from user input.
     */
    private static final String DATETIME_REGEX = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) \\d{2}:\\d{2}";
    /**
     * Regex for datetime from saved format.
     */
    private static final String LOCALDATETIME_REGEX = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])T\\d{2}:\\d{2}";
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command.\n\n"
            + "These are list of commands:\n"
            + "todo <description>\n"
            + "deadline <description> /by <YYYY-MM-DD hh:mm>\n"
            + "event <description> /from <YYYY-MM-DD hh:mm> /by <YYYY-MM-DD hh:mm>\n"
            + "mark <index>\n"
            + "unmark <index>\n"
            + "delete <index>\n"
            + "find <keyword>\n"
            + "sort\n"
            + "list\n"
            + "bye\n";



    /**
     * Checks if a string is a valid datetime.
     * @param datetime String to be checked
     * @return Boolean that represents whether the string is a valid datetime
     */
    public static boolean isValidDateTime(String datetime) {
        datetime = datetime.strip();
        return Pattern.matches(DATETIME_REGEX, datetime) || Pattern.matches(LOCALDATETIME_REGEX, datetime);
    }

    /**
     * Parses a string to a LocalDateTime object.
     * @param datetime String to be parsed
     * @return LocalDateTime object
     * @throws GrumpyGordonDateTimeFormatException If the datetime format is invalid
     */
    public static LocalDateTime parseDateTime(String datetime) throws GrumpyGordonDateTimeFormatException {
        if (isValidDateTime(datetime) && Pattern.matches(DATETIME_REGEX, datetime)) {
            try {
                datetime = datetime.strip();
                String[] arr = datetime.split(" ");
                String[] date = arr[0].split("-");
                String[] time = arr[1].split(":");
                return LocalDateTime.of(
                        Integer.parseInt(date[0]),
                        Integer.parseInt(date[1]),
                        Integer.parseInt(date[2]),
                        Integer.parseInt(time[0]),
                        Integer.parseInt(time[1]));
            } catch (DateTimeException e) {
                throw new GrumpyGordonDateTimeFormatException("That datetime does not exist.\n");
            }
        } else if (isValidDateTime(datetime) && Pattern.matches(LOCALDATETIME_REGEX, datetime)) {
            try {
                return LocalDateTime.parse(datetime);
            } catch (DateTimeException e) {
                throw new GrumpyGordonDateTimeFormatException("That datetime does not exist.\n");
            }
        } else {
            throw new GrumpyGordonDateTimeFormatException("Invalid datetime format.\n");
        }
    }
    /**
     * Parses a string to a Task object.
     * @param line String to be parsed
     * @return Task object
     * @throws GrumpyGordonException If the saved format is invalid
     */
    public static Task parseStringToTask(String line) throws GrumpyGordonException {
        String[] parts = line.split(SAVED_FORMAT_SEPARATOR_REGEX);

        if (parts.length < 3) {
            throw new GrumpyGordonInitialisationException("Saved data is corrupted.\n");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
            case "T":
                // Parse the saved format for Todo
                // Example: "T | 0 | Sleep"
                return new Todo(description, isDone);
            case "D":
                // Parse the saved format for Deadline
                // Example: "D | 1 | Buy groceries | 2023-08-31 12:00"
                String deadlineBy = parts[3];
                return new Deadline(description, parseDateTime(deadlineBy), isDone);
            case "E":
                // Parse the saved format for Event
                // Example: "E | 0 | Team meeting | 2023-09-01 12:00 | 2023-09-02 14:00"
                String eventFrom = parts[3];
                String eventTo = parts[4];
                return new Event(description, parseDateTime(eventFrom), parseDateTime(eventTo), isDone);
            default:
                break;
            }
        } catch (GrumpyGordonDateTimeFormatException e) {
            throw new GrumpyGordonInitialisationException("Saved data could not be parsed.\n");
        }
        throw new GrumpyGordonInitialisationException("Saved data could not be parsed.\n");
    }

    /**
     * Parses a string to a TaskList object.
     * @param deadlineInfo String to be parsed
     * @return TaskList object
     */
    public static String[] parseDeadlineInfo(String deadlineInfo) {
        String desc = deadlineInfo.split(" /by ")[0];
        String by = deadlineInfo.split(" /by ")[1];
        return new String[] {desc, by};
    }

    /**
     * Parses a string to a TaskList object.
     * @param eventInfo String to be parsed
     * @return TaskList object
     */
    public static String[] parseEventInfo(String eventInfo) {
        String desc = eventInfo.split(" /from ")[0];
        String from = eventInfo.split(" /from ")[1].split(" /to ")[0];
        String to = eventInfo.split(" /from ")[1].split(" /to ")[1];
        return new String[] {desc, from, to};
    }
    /**
     * Parses a string to a TaskList object.
     * @param userInput String to be parsed
     * @return TaskList object
     * @throws GrumpyGordonException If the command is invalid
     */
    public static Command parseCommand(String userInput, TaskList tasks) throws GrumpyGordonInvalidCommandException {
        userInput = userInput.strip();
        String[] parts = userInput.split(SPACE_REGEX, 2);

        if (parts.length == 0) {
            throw new GrumpyGordonInvalidCommandException("Command cannot be empty.\n");
        }

        String command = parts[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "sort":
            return new SortCommand();
        case "mark":
            return parseMarkCommand(parts, userInput, tasks);
        case "unmark":
            return parseUnmarkCommand(parts, userInput, tasks);
        case "delete":
            return parseDeleteCommand(parts, userInput, tasks);
        case "find":
            return parseFindCommand(parts, userInput);
        case "todo":
            return parseTodoCommand(parts);
        case "deadline":
            return parseDeadlineCommand(parts);
        case "event":
            return parseEventCommand(parts);
        default:
            break;
        }
        throw new GrumpyGordonInvalidCommandException(INVALID_COMMAND_MESSAGE);
    }

    /**
     * Parses a string to a MarkCommand object.
     * @param parts Array containing the components of the user input
     * @param userInput String to be parsed
     * @param tasks List of all tasks
     * @return MarkCommand object
     * @throws GrumpyGordonException If the command is invalid
     */
    public static Command parseMarkCommand(
            String[] parts, String userInput, TaskList tasks) throws GrumpyGordonInvalidCommandException {
        int taskIndex;
        if (parts.length == 1) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for mark is incorrect.\n");
        }
        assert parts.length > 1 : "parts.length should be more than 1";
        if (!Pattern.matches(MARK_REGEX, userInput)) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for mark is incorrect.\n");
        }

        try {
            taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
        }

        if (taskIndex < 0 || taskIndex > tasks.size() - 1) {
            throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
        }

        return new MarkCommand(taskIndex);
    }
    /**
     * Parses a string to a UnmarkCommand object.
     * @param parts Array containing the components of the user input
     * @param userInput String to be parsed
     * @param tasks List of all tasks
     * @return UnmarkCommand object
     * @throws GrumpyGordonInvalidCommandException If the command is invalid
     */
    public static Command parseUnmarkCommand(
        String[] parts, String userInput, TaskList tasks) throws GrumpyGordonInvalidCommandException {
        int taskIndex;
        if (parts.length == 1) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for unmark is incorrect.\n");
        }
        assert parts.length > 1 : "parts.length should be more than 1";
        if (!Pattern.matches(UNMARK_REGEX, userInput)) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for unmark is incorrect.\n");
        }
        try {
            taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
        }

        if (taskIndex < 0 || taskIndex > tasks.size() - 1) {
            throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
        }

        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses a string to a DeleteCommand object.
     * @param parts Array containing the components of the user input
     * @param userInput String to be parsed
     * @param tasks List of all tasks
     * @return DeleteCommand object
     * @throws GrumpyGordonInvalidCommandException If the command is invalid
     */
    public static Command parseDeleteCommand(
            String[] parts, String userInput, TaskList tasks) throws GrumpyGordonInvalidCommandException {
        int taskIndex;
        if (parts.length == 1) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for delete is incorrect.\n");
        }
        assert parts.length > 1 : "parts.length should be more than 1";
        if (!Pattern.matches(DELETE_REGEX, userInput)) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for delete is incorrect.\n");
        }
        try {
            taskIndex = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
        }

        if (taskIndex < 0 || taskIndex > tasks.size() - 1) {
            throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
        }

        return new DeleteCommand(taskIndex);
    }
    /**
     * Parses a string to a FindCommand object.
     * @param parts Array containing the components of the user input
     * @param userInput String to be parsed
     * @return FindCommand object
     * @throws GrumpyGordonInvalidCommandException If the command is invalid
     */
    public static Command parseFindCommand(
            String[] parts, String userInput) throws GrumpyGordonInvalidCommandException {
        if (parts.length == 1) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for find is incorrect.\n");
        }
        assert parts.length > 1 : "parts.length should be more than 1";
        if (!Pattern.matches(FIND_REGEX, userInput)) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for find is incorrect.\n");
        }
        String pattern = userInput.split(" ", 2)[1];
        return new FindCommand(pattern);
    }
    /**
     * Parses a string to a TodoCommand object.
     * @param parts Array containing the components of the user input
     * @return TodoCommand object
     * @throws GrumpyGordonInvalidCommandException If the command is invalid
     */
    public static Command parseTodoCommand(String[] parts) throws GrumpyGordonInvalidCommandException {
        if (parts.length == 1) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for todo is incorrect.\n");
        }
        assert parts.length > 1 : "parts.length should be more than 1";
        String todoInfo = parts[1];
        if (todoInfo.isBlank()) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for todo is incorrect.\n");
        }
        return new TodoCommand(todoInfo);
    }
    /**
     * Parses a string to a DeadlineCommand object.
     * @param parts Array containing the components of the user input
     * @return DeadlineCommand object
     * @throws GrumpyGordonInvalidCommandException If the command is invalid
     */
    public static Command parseDeadlineCommand(String[] parts) throws GrumpyGordonInvalidCommandException {
        if (parts.length == 1) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for deadline is incorrect.\n");
        }
        assert parts.length > 1 : "parts.length should be more than 1";
        String deadlineInfo = parts[1];
        if (!Pattern.matches(DEADLINE_INFO_REGEX, deadlineInfo)) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for deadline is incorrect.\n");
        }
        String[] args = Parser.parseDeadlineInfo(deadlineInfo);
        try {
            return new DeadlineCommand(args[0], parseDateTime(args[1]));
        } catch (GrumpyGordonDateTimeFormatException e) {
            throw new GrumpyGordonInvalidCommandException(e.getMessage());
        }
    }
    /**
     * Parses a string to a EventCommand object.
     * @param parts Array containing the components of the user input
     * @return EventCommand object
     * @throws GrumpyGordonInvalidCommandException If the command is invalid
     */
    public static Command parseEventCommand(String[] parts) throws GrumpyGordonInvalidCommandException {
        if (parts.length == 1) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for event is incorrect.\n");
        }
        assert parts.length > 1 : "parts.length should be more than 1";
        String eventInfo = parts[1];
        if (!Pattern.matches(EVENT_INFO_REGEX, eventInfo)) {
            throw new GrumpyGordonInvalidCommandException("Command syntax for event is incorrect.\n");
        }
        String[] args = Parser.parseEventInfo(eventInfo);
        try {
            return new EventCommand(args[0], parseDateTime(args[1]), parseDateTime(args[2]));
        } catch (GrumpyGordonDateTimeFormatException e) {
            throw new GrumpyGordonInvalidCommandException(e.getMessage());
        }
    }
}
