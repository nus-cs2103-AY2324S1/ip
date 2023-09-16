package jarvis.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.commands.AddCommand;
import jarvis.commands.Command;
import jarvis.commands.DeleteCommand;
import jarvis.commands.ExitCommand;
import jarvis.commands.FindCommand;
import jarvis.commands.IncorrectCommand;
import jarvis.commands.ListCommand;
import jarvis.commands.MarkCommand;
import jarvis.commands.RemindCommand;

/**
 * Represents the Parser Class.
 * Responsible for parsing user input.
 *
 * @author Shishir
 */
public class Parser {

    private static final String INVALID_COMMAND = "I'm sorry, I couldn't understand that. Please try again!";
    private static final String INVALID_INDEX = "Please enter a valid index!";
    private static final String INVALID_DEADLINE = "Please ensure the entered deadline task is valid!";
    private static final String INVALID_EVENT = "Please ensure the entered event task is valid!";
    private static final String INVALID_DATE = "Please enter the date & time in a valid format! (DD/MM/YY HHMM)";
    private static final String INVALID_RANGE = "Please ensure that the date range is valid!";

    /**
     * Returns a command based on user input.
     * @param fullCommand String representation of user input.
     * @return Command based on user input.
     */
    public static Command parse(String fullCommand) {

        String[] split = fullCommand.split(" ", 2);

        switch(split[0]) {
        case "bye":
            return parseExit(split);
        case "list":
            return parseList(split);
        case "mark":
        case "unmark":
            return parseMark(split);
        case "todo":
        case "deadline":
        case "event":
            return parseTask(split);
        case "delete":
            return parseDelete(split);
        case "find":
            return parseFind(split);
        case "remind":
            return parseRemind(split);
        default:
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

        switch(split[0]) {
        case "deadline":
            return parseDeadline(split);
        case "event":
            return parseEvent(split);
        case "todo":
            return new AddCommand(split[1]);
        default:
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
