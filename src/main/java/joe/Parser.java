package joe;

import joe.commands.ByeCommand;
import joe.commands.Command;
import joe.commands.DeadlineCommand;
import joe.commands.DeleteCommand;
import joe.commands.EventCommand;
import joe.commands.InvalidCommand;
import joe.commands.ListCommand;
import joe.commands.MarkCommand;
import joe.commands.TodoCommand;
import joe.commands.UnmarkCommand;
import joe.exceptions.JoeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(\\S+)\\s?(.*)$");
    private static final String DATETIME_FORMAT = "d/M/yyyy HHmm";
    private static final String FAILED_TO_PARSE_DATE_MESSAGE = "Failed to parse the date.\nPlease ensure it is a valid datetime following the format <d/M/yyyy HHmm>";
    private static final String FAILED_TO_PARSE_INDEX_MESSAGE = "Failed to parse index";

    public static Command parse(String input) {
        Matcher m = COMMAND_PATTERN.matcher(input.trim());

        if (!m.matches()) {
            return new InvalidCommand("Invalid Command Format");
        }

        CommandType type = parseType(m.group(1));
        String args = m.group(2).trim();

        switch (type) {
        case bye:
            if (!args.isEmpty()) {
                return new InvalidCommand("Invalid Command! Too many arguments!");
            }
            return new ByeCommand();
        case list:
            if (!args.isEmpty()) {
                return new InvalidCommand("Invalid Command! Too many arguments!");
            }
            return new ListCommand();
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

    private static Command handleMark(String args) {
        try {
            int idx = parseIndexArgs(args);
            return new MarkCommand(idx);
        } catch (JoeException e) {
            return new InvalidCommand("Invalid arguments for mark\nPlease follow: mark <task_num>");
        }
    }

    private static Command handleUnmark(String args) {
        try {
            int idx = parseIndexArgs(args);
            return new UnmarkCommand(idx);
        } catch (JoeException e) {
            return new InvalidCommand("Invalid arguments for unmark\nPlease follow: unmark <task_num>");
        }
    }

    private static Command handleTodo(String args) {
        Pattern p = Pattern.compile("(\\S.*)");
        Matcher m = p.matcher(args.trim());

        if (!m.matches()) {
            return new InvalidCommand("Invalid arguments for todo\nPlease follow: todo <task>");
        }
        return new TodoCommand(m.group(1));
    }

    private static Command handleDeadline(String args) {
        Pattern p = Pattern.compile("(\\S.*)\\s+/by\\s+(\\d{1,2}/\\d{1,2}/\\d{4} \\d{4})");
        Matcher m = p.matcher(args.trim());

        if (!m.matches()) {
            return new InvalidCommand("Invalid arguments for deadline\nPlease follow: deadline <task> /by <d/M/yyyy HHmm>");
        }

        try {
            LocalDateTime by = LocalDateTime.parse(m.group(2), DateTimeFormatter.ofPattern(DATETIME_FORMAT));

            return new DeadlineCommand(m.group(1), by);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(FAILED_TO_PARSE_DATE_MESSAGE);
        }
    }

    private static Command handleEvent(String args) {
        Pattern p = Pattern.compile("(\\S.*)\\s+/from\\s+(\\d{1,2}/\\d{1,2}/\\d{4} \\d{4})\\s+/to\\s+(\\d{1,2}/\\d{1,2}/\\d{4} " + "\\d{4})");
        Matcher m = p.matcher(args.trim());
        if (!m.matches()) {
            return new InvalidCommand("Invalid arguments for event\nPlease follow: event <task> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
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
            int idx = parseIndexArgs(args);
            return new DeleteCommand(idx);
        } catch (JoeException e) {
            return new InvalidCommand("Invalid arguments for delete\nPlease follow: delete <task_num>");
        }
    }

    private static Command handleInvalidKeyword() {
        StringBuilder sb = new StringBuilder();
        for (CommandType cmd : CommandType.values()) {
            if (CommandType.INVALID.equals(cmd)) {
                // Skip INVALID when listing all the valid commands
                continue;
            }

            sb.append(cmd.toString());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2); // Remove extra ", " at the end
        String msg = String.format("Invalid Command Keyword!%nHere is a list of valid commands: %s", sb);
        return new InvalidCommand(msg);
    }

    static int parseIndexArgs(String args) throws JoeException {
        Pattern indexPattern = Pattern.compile("^(\\d+)$");
        Matcher m = indexPattern.matcher(args.trim());

        if (!m.matches()) {
            throw new JoeException(FAILED_TO_PARSE_INDEX_MESSAGE);
        }

        String idx = m.group(1);
        return Integer.parseInt(idx);
    }

    private enum CommandType {
        list, todo, deadline, event, mark, unmark, delete, bye, INVALID
    }
}
