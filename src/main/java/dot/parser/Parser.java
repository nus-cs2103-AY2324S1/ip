package dot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dot.commands.ByeCommand;
import dot.commands.Command;
import dot.commands.DeadlineCommand;
import dot.commands.DeleteCommand;
import dot.commands.EventCommand;
import dot.commands.FindCommand;
import dot.commands.HelpCommand;
import dot.commands.ListCommand;
import dot.commands.MarkCommand;
import dot.commands.TodoCommand;
import dot.commands.UnmarkCommand;
import dot.commands.WhatsgoingonCommand;
import dot.errors.DotException;
import dot.errors.TaskError;
import dot.tasks.TaskList;

/**
 * This is the parser for Dot, which converts String inputs into
 * formatted strings, or other desired objects.
 */
public class Parser {

    /**
     * Parses a dateInput in the format dd/MM/yyyy into a LocalDateTime
     * We will standardise hhmmss as 000000. It is important to run
     * a Validation function on the input before using a Parser fn.
     *
     * @param dateInput in the format dd/MM/yyyy into a LocalDateTime.
     * @return corresponding LocalDateTime object.
     */
    public static LocalDateTime parseDateInputIntoDateTime(String dateInput) {
        assert !dateInput.isBlank() : "dateInput is not supposed to be an empty string";
        String[] dayMonthYear = dateInput.split("/");
        return LocalDateTime.parse(String.format("%s-%02d-%02dT00:00:00", dayMonthYear[2],
                Integer.valueOf(dayMonthYear[1]), Integer.valueOf(dayMonthYear[0])));
    }

    /**
     * Parses from MMM dd yyyy HHmm to yyyy-MM-dd HHmm.
     * Reference: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/
     * DateTimeFormatter.html
     *
     * @param displayDateTime of format MMM dd yyyy ha.
     * @return String in format yyyy-MM-dd HHmm i.e. user input.
     */
    public static String parseDisplayDatetimeToStorageDatetime(String displayDateTime) {
        assert !displayDateTime.isBlank() : "displayDateTime is not supposed to be an empty string";
        try {
            DateTimeFormatter displayDateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy ha");
            LocalDateTime displayLocalDateTime = LocalDateTime.parse(displayDateTime, displayDateTimeFormat);
            DateTimeFormatter storageDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return displayLocalDateTime.format(storageDateTimeFormat);
        } catch (DateTimeParseException e) {
            // This shouldn't happen, but will default to TimeDescription
            return displayDateTime;
        }
    }

    /**
     * Parses the user's input into the appropriate command.
     * Heavily utilises the sister class Validation.
     *
     * @param input       This is the user input.
     * @param dotTaskList This is the TaskList which encapsulates the Task and operations.
     * @return The appropriate Command.
     * @throws DotException On detected error.
     */
    public static Command parseInputToCommand(String input, TaskList dotTaskList) throws DotException {
        switch (input.strip()) {
        case "bye":
            return new ByeCommand(); // no break due to return
        case "help":
            return new HelpCommand(); // no break due to return
        case "list":
        case "ls":
            return new ListCommand(dotTaskList); // no break due to return
        default:
            if (Validation.isValidCommand(input, "mark")) {
                return getMarkCommand(input, dotTaskList);
            } else if (Validation.isValidCommand(input, "unmark")) {
                return getUnmarkCommand(input, dotTaskList);
            } else if (Validation.isValidCommand(input, "todo")) {
                return getTodoCommand(input, dotTaskList);
            } else if (Validation.isValidCommand(input, "deadline")) {
                return getDeadlineCommand(input, dotTaskList);
            } else if (Validation.isValidCommand(input, "event")) {
                return getEventCommand(input, dotTaskList);
            } else if (Validation.isValidCommand(input, "delete")) {
                return getDeleteCommand(input, dotTaskList);
            } else if (Validation.isValidCommand(input, "whatsgoingon")) {
                return getWhatsgoingonCommand(input, dotTaskList);
            } else if (Validation.isValidCommand(input, "find")) {
                return getFindCommand(input, dotTaskList);
            }
            throw new DotException("Unknown command.", TaskError.ERR_READING_COMMAND); // no break due to throw
        }
    }

    private static Command getMarkCommand(String input, TaskList dotTaskList) throws DotException {
        assert !input.isBlank() : "input is not supposed to be an empty string";
        assert dotTaskList != null : "dotTaskList is not supposed to be null";
        int position = Validation.getIntIfValidCommandSpaceNumber(input, TaskError.ERR_USING_MARK);
        return new MarkCommand(position, dotTaskList);
    }

    private static Command getUnmarkCommand(String input, TaskList dotTaskList) throws DotException {
        assert !input.isBlank() : "input is not supposed to be an empty string";
        assert dotTaskList != null : "dotTaskList is not supposed to be null";
        int position = Validation.getIntIfValidCommandSpaceNumber(input, TaskError.ERR_USING_UNMARK);
        return new UnmarkCommand(position, dotTaskList);
    }

    private static Command getTodoCommand(String input, TaskList dotTaskList) throws DotException {
        assert !input.isBlank() : "input is not supposed to be an empty string";
        assert dotTaskList != null : "dotTaskList is not supposed to be null";
        String restOfString = Validation.getDescIfValidCommandSpaceDesc(input, "todo",
                "task description", TaskError.ERR_USING_TODO);
        return new TodoCommand(restOfString, dotTaskList);
    }

    private static Command getDeadlineCommand(String input, TaskList dotTaskList) throws DotException {
        assert !input.isBlank() : "input is not supposed to be an empty string";
        assert dotTaskList != null : "dotTaskList is not supposed to be null";
        String[] args = Validation.getArgsIfValidDeadlineFormat(input);
        return new DeadlineCommand(args[0], args[1], dotTaskList);
    }

    private static Command getEventCommand(String input, TaskList dotTaskList) throws DotException {
        assert !input.isBlank() : "input is not supposed to be an empty string";
        assert dotTaskList != null : "dotTaskList is not supposed to be null";
        String[] args = Validation.getArgsIfValidEventFormat(input);
        return new EventCommand(args[0], args[1], args[2], dotTaskList);
    }

    private static Command getDeleteCommand(String input, TaskList dotTaskList) throws DotException {
        assert !input.isBlank() : "input is not supposed to be an empty string";
        assert dotTaskList != null : "dotTaskList is not supposed to be null";
        int position = Validation.getIntIfValidCommandSpaceNumber(input, TaskError.ERR_DELETING_TASK);
        return new DeleteCommand(position, dotTaskList);
    }

    private static Command getWhatsgoingonCommand(String input, TaskList dotTaskList) throws DotException {
        assert !input.isBlank() : "input is not supposed to be an empty string";
        assert dotTaskList != null : "dotTaskList is not supposed to be null";
        String restOfString = Validation.getDescIfValidCommandSpaceDesc(input,
                "whatsgoingon", "date", TaskError.ERR_USING_WHATSGOINGON);
        if (!(Validation.isValidDate(restOfString))) {
            throw new DotException("Incorrect format for data, use dd/MM/yyyy",
                    TaskError.ERR_USING_WHATSGOINGON);
        }
        LocalDateTime parsedLocalDateTime = Parser.parseDateInputIntoDateTime(restOfString);
        return new WhatsgoingonCommand(parsedLocalDateTime, dotTaskList);
    }

    private static Command getFindCommand(String input, TaskList dotTaskList) throws DotException {
        assert !input.isBlank() : "input is not supposed to be an empty string";
        assert dotTaskList != null : "dotTaskList is not supposed to be null";
        String restOfString = Validation.getDescIfValidCommandSpaceDesc(input,
                "find", "query string", TaskError.ERR_USING_FIND);
        return new FindCommand(restOfString, dotTaskList);
    }
}
