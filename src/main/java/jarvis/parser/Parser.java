package jarvis.parser;

import static jarvis.exceptions.ExceptionMessages.INVALID_COMMAND;
import static jarvis.exceptions.ExceptionMessages.INVALID_DATE;
import static jarvis.exceptions.ExceptionMessages.INVALID_DEADLINE;
import static jarvis.exceptions.ExceptionMessages.INVALID_EVENT;
import static jarvis.exceptions.ExceptionMessages.INVALID_INDEX;
import static jarvis.exceptions.ExceptionMessages.INVALID_RANGE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.commands.AddCommand;
import jarvis.commands.Command;
import jarvis.commands.CommandType;
import jarvis.commands.DeleteCommand;
import jarvis.commands.ExitCommand;
import jarvis.commands.FindCommand;
import jarvis.commands.IncorrectCommand;
import jarvis.commands.ListCommand;
import jarvis.commands.MarkCommand;
import jarvis.commands.RemindCommand;
import jarvis.tasks.TaskType;

/**
 * Represents the Parser Class.
 * Responsible for parsing user input.
 *
 * @author Shishir
 */
public class Parser {

    /**
     * Returns a command based on user input.
     * @param fullCommand String representation of user input.
     * @return Command based on user input.
     */
    public static Command parse(String fullCommand) {
        try {
            String[] split = fullCommand.split(" ", 2);
            CommandType commandType = CommandType.valueOf(split[0].toUpperCase());
            switch(commandType) {
            case BYE:
                return parseExit(split);
            case LIST:
                return parseList(split);
            case MARK:
            case UNMARK:
                return parseMark(split);
            case TODO:
            case DEADLINE:
            case EVENT:
                return parseTask(split);
            case DELETE:
                return parseDelete(split);
            case FIND:
                return parseFind(split);
            case REMIND:
                return parseRemind(split);
            default:
                return new IncorrectCommand(INVALID_COMMAND);
            }
        } catch (IllegalArgumentException e) {
            return new IncorrectCommand(INVALID_COMMAND);
        }
    }

    private static Command parseMark(String[] split) {
        // Check if mark is receiving any input or receiving extra input
        if (split.length != 2 || split[1].isBlank()) {
            return new IncorrectCommand(INVALID_COMMAND);
        }

        Integer index = getNumber(split[1]);

        if (index == null) {
            return new IncorrectCommand(INVALID_INDEX);
        }

        assert !split[0].isBlank();
        return new MarkCommand(index, split[0]);
    }

    private static Command parseTask(String[] split) {
        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand(INVALID_COMMAND);
        }

        try {
            TaskType taskType = TaskType.valueOf(split[0].toUpperCase());
            switch(taskType) {
            case DEADLINE:
                return parseDeadline(split);
            case EVENT:
                return parseEvent(split);
            case TODO:
                return new AddCommand(split[1]);
            default:
                return new IncorrectCommand(INVALID_COMMAND);
            }
        } catch (IllegalArgumentException e) {
            return new IncorrectCommand(INVALID_COMMAND);
        }
    }

    private static Command parseFind(String[] split) {
        if (split.length <= 1) {
            return new IncorrectCommand(INVALID_COMMAND);
        }
        return new FindCommand(split[1]);
    }

    private static Command parseList(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand(INVALID_COMMAND);
        }
        return new ListCommand();
    }

    private static Command parseExit(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand(INVALID_COMMAND);
        }
        return new ExitCommand();
    }

    private static Integer getNumber(String split) {
        try {
            int index = Integer.parseInt(split);
            return index <= 0 ? null : index;
        } catch (NumberFormatException exp) {
            return null;
        }
    }

    private static Command parseDelete(String[] split) {
        if (split.length != 2 || split[1].isBlank()) {
            return new IncorrectCommand(INVALID_COMMAND);
        }

        Integer index = getNumber(split[1]);

        if (index == null) {
            return new IncorrectCommand(INVALID_INDEX);
        }
        assert !split[0].isBlank();
        return new DeleteCommand(index);
    }

    private static Command parseDeadline(String[] split) {
        if (!split[1].contains(" /by ")) {
            return new IncorrectCommand(INVALID_DEADLINE);
        }

        String[] task = split[1].split(" /by ", 2);

        if (task.length <= 1 || task[1].isBlank() || task[0].isBlank()) {
            return new IncorrectCommand(INVALID_DEADLINE);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            return new AddCommand(task[0], LocalDateTime.parse(task[1], formatter));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(INVALID_DATE);
        }
    }

    private static Command parseEvent(String[] split) {
        if (!split[1].contains(" /from ")) {
            return new IncorrectCommand(INVALID_EVENT);
        }

        String[] task = split[1].split(" /from ", 2);

        // Check if task entered is empty
        if (task.length <= 1 || task[1].isBlank() || task[0].isBlank()) {
            return new IncorrectCommand(INVALID_EVENT);
        }

        // Check if /to is present
        if (!task[1].contains(" /to ")) {
            return new IncorrectCommand(INVALID_EVENT);
        }

        String[] to = task[1].split(" /to ", 2);

        if (to.length <= 1 || to[1].isBlank() || to[0].isBlank()) {
            return new IncorrectCommand(INVALID_EVENT);
        }

        return parseDate(to, task);
    }

    private static Command parseDate(String[] to, String[] task) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
            LocalDateTime from = LocalDateTime.parse(to[0], formatter);
            LocalDateTime till = LocalDateTime.parse(to[1], formatter);
            if (from.isAfter(till) || from.isEqual(till)) {
                return new IncorrectCommand(INVALID_RANGE);
            }
            return new AddCommand(task[0], from, till);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(INVALID_DATE);
        }
    }

    private static Command parseRemind(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand(INVALID_COMMAND);
        }
        return new RemindCommand();
    }
}
