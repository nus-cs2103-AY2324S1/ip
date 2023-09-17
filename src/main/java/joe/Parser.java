package joe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import joe.commands.ByeCommand;
import joe.commands.Command;
import joe.commands.DeadlineCommand;
import joe.commands.DeleteCommand;
import joe.commands.EventCommand;
import joe.commands.FindCommand;
import joe.commands.InvalidCommand;
import joe.commands.ListCommand;
import joe.commands.MarkCommand;
import joe.commands.TodoCommand;
import joe.commands.UnmarkCommand;
import joe.exceptions.JoeException;

/**
 * Parses user input and generates corresponding Command objects.
 */
public class Parser {
    private enum CommandType {
        list, todo, deadline, event, mark, unmark, delete, bye, find, findall, findmatch, INVALID
    }

    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(\\S+)\\s?(.*)$");
    private static final String DATETIME_FORMAT = "d/M/yyyy HHmm";
    private static final String FAILED_TO_PARSE_DATE_MESSAGE = "Failed to parse the date.\n"
            + "Please ensure it is a valid datetime following the format <d/M/yyyy HHmm>";
    private static final String FAILED_TO_PARSE_INDEX_MESSAGE = "Failed to parse index";
    private static final String INVALID_ARGS_MARK_MESSAGE = "Invalid arguments for mark\n"
            + "Please follow: mark <task_num>";
    private static final String INVALID_ARGS_UNMARK_MESSAGE = "Invalid arguments for unmark\n"
            + "Please follow: unmark <task_num>";
    private static final String INVALID_ARGS_DELETE_MESSAGE = "Invalid arguments for delete\n"
            + "Please follow: delete <task_num>";
    private static final String INVALID_ARGS_FIND_MESSAGE = "Invalid arguments for find\n"
            + "Please follow: find <search_word>";
    private static final String INVALID_ARGS_TODO_MESSAGE = "Invalid arguments for todo\n"
            + "Please follow: todo <task>";
    private static final String INVALID_ARGS_DEADLINE_MESSAGE = "Invalid arguments for deadline\n"
            + "Please follow: deadline <task> /by <d/M/yyyy HHmm>";
    private static final String INVALID_ARGS_EVENT_MESSAGE = "Invalid arguments for event\n"
            + "Please follow: event <task> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>";

    private static final String TOO_MANY_ARGS_MESSAGE = "Invalid Command! Too many arguments!";


    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The corresponding Command object.
     */
    public static Command parseUserInput(String input) {
        //Validate input is in correct format
        Matcher m = COMMAND_PATTERN.matcher(input.trim());
        if (!m.matches()) {
            return new InvalidCommand("Invalid Command Format");
        }

        //Split input into type and args
        CommandType type = parseType(m.group(1));
        String args = m.group(2).trim();

        return makeCommand(type, args);
    }

    private static Command makeCommand(CommandType type, String args) {
        switch (type) {
        case bye:
            return handleBye(args);
        case list:
            return handleList(args);
        case mark:
            return handleMark(args);
        case unmark:
            return handleUnmark(args);
        case todo:
            return handleTodo(args);
        case deadline:
            return handleDeadline(args);
        case event:
            return handleEvent(args);
        case delete:
            return handleDelete(args);
        case find:
            return handleFind(args, FindCommand.DESC);
        case findmatch:
            return handleFind(args, FindCommand.DESC_MATCH_CASE);
        case findall:
            return handleFind(args, FindCommand.ALL);
        default:
            return handleInvalidKeyword();
        }
    }

    private static CommandType parseType(String input) {
        try {
            return CommandType.valueOf(input);
        } catch (IllegalArgumentException e) {
            return CommandType.INVALID;
        }
    }

    private static Command handleBye(String args) {
        if (!args.isEmpty()) {
            return new InvalidCommand(TOO_MANY_ARGS_MESSAGE);
        }
        return new ByeCommand();
    }

    private static Command handleList(String args) {
        if (!args.isEmpty()) {
            return new InvalidCommand(TOO_MANY_ARGS_MESSAGE);
        }
        return new ListCommand();
    }

    private static Command handleMark(String args) {
        try {
            int idx = parseArgsForIndex(args);

            //Index should be at least 1
            assert idx >= 1;

            return new MarkCommand(idx);
        } catch (JoeException e) {
            return new InvalidCommand(INVALID_ARGS_MARK_MESSAGE);
        }
    }

    private static Command handleUnmark(String args) {
        try {
            int idx = parseArgsForIndex(args);

            //Index should be at least 1
            assert idx >= 1;

            return new UnmarkCommand(idx);
        } catch (JoeException e) {
            return new InvalidCommand(INVALID_ARGS_UNMARK_MESSAGE);
        }
    }

    private static Command handleTodo(String args) {
        Pattern p = Pattern.compile("(\\S.*)");
        Matcher m = p.matcher(args.trim());

        if (!m.matches()) {
            return new InvalidCommand(INVALID_ARGS_TODO_MESSAGE);
        }

        return new TodoCommand(m.group(1));
    }

    private static Command handleDeadline(String args) {
        Pattern p = Pattern.compile("(\\S.*)\\s+/by\\s+(\\d{1,2}/\\d{1,2}/\\d{4} \\d{4})");
        Matcher m = p.matcher(args.trim());

        if (!m.matches()) {
            return new InvalidCommand(INVALID_ARGS_DEADLINE_MESSAGE);
        }

        try {
            LocalDateTime by = LocalDateTime.parse(m.group(2), DateTimeFormatter.ofPattern(DATETIME_FORMAT));

            return new DeadlineCommand(m.group(1), by);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(FAILED_TO_PARSE_DATE_MESSAGE);
        }
    }

    private static Command handleEvent(String args) {
        Pattern p = Pattern.compile("(\\S.*)\\s+/from\\s+(\\d{1,2}/\\d{1,2}/\\d{4} \\d{4})\\s+"
                + "/to\\s+(\\d{1,2}/\\d{1,2}/\\d{4} \\d{4})");
        Matcher m = p.matcher(args.trim());
        if (!m.matches()) {
            return new InvalidCommand(INVALID_ARGS_EVENT_MESSAGE);
        }

        try {
            LocalDateTime from = LocalDateTime.parse(m.group(2), DateTimeFormatter.ofPattern(DATETIME_FORMAT));
            LocalDateTime to = LocalDateTime.parse(m.group(3), DateTimeFormatter.ofPattern(DATETIME_FORMAT));

            return new EventCommand(m.group(1), from, to);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(FAILED_TO_PARSE_DATE_MESSAGE);
        }
    }

    private static Command handleDelete(String args) {
        try {
            int idx = parseArgsForIndex(args);

            //Index should be at least 1
            assert idx >= 1;

            return new DeleteCommand(idx);
        } catch (JoeException e) {
            return new InvalidCommand(INVALID_ARGS_DELETE_MESSAGE);
        }
    }

    private static Command handleFind(String args, int searchType) {
        if (args.trim().isEmpty()) {
            return new InvalidCommand(INVALID_ARGS_FIND_MESSAGE);
        }
        return new FindCommand(args.trim(), searchType);
    }

    private static Command handleInvalidKeyword() {
        StringBuilder sb = new StringBuilder();
        for (CommandType cmd : CommandType.values()) {
            if (CommandType.INVALID.equals(cmd)) {
                //Skips INVALID when listing all the valid commands
                continue;
            }

            sb.append(cmd.toString());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2); //Removes extra ", " at the end
        String msg = String.format("Invalid Command Keyword!%nHere is a list of valid commands: %s", sb);
        return new InvalidCommand(msg);
    }

    private static int parseArgsForIndex(String args) throws JoeException {
        Pattern indexPattern = Pattern.compile("^(\\d+)$");
        Matcher m = indexPattern.matcher(args.trim());

        if (!m.matches()) {
            throw new JoeException(FAILED_TO_PARSE_INDEX_MESSAGE);
        }

        int idx = Integer.parseInt(m.group(1));

        if (idx < 1) {
            throw new JoeException(FAILED_TO_PARSE_INDEX_MESSAGE);
        }

        return idx;
    }
}
