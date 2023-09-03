package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DueCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;

import duke.data.exception.DukeException;
import duke.data.exception.DukeExceptionType;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a service which processes the input the user types into the appropriate commands.
 * <p>
 * It contains methods which uses regex to match arguments required for each command and initializing
 * the different types of commands.
 */
public class Parser {

    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern TASK_NUMBER_ARGS_FORMAT = Pattern.compile("(?<taskNumber>.+)");
    public static final Pattern DEADLINE_ARGS_FORMAT =
            Pattern.compile("(?<description>.*?)\\s+/by\\s+(?<deadline>.*)");
    public static final Pattern EVENT_ARGS_FORMAT =
            Pattern.compile("(?<description>.*?)\\s+/from\\s+(?<fromDate>.*?)\\s+/to\\s+(?<toDate>.*)");

    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Processes the input entered by the user by extracting the command word
     * and passing the arguments to the relevant command helper functions.
     *
     * @param userInput User's input from the command line user interface.
     * @return Command
     * @throws DukeException Errors relating to commands
     */
    public Command parse(String userInput) throws DukeException {
        Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new DukeException(DukeExceptionType.UNKNOWN_COMMAND);
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case MarkCommand.COMMAND_WORD:
            return prepareMarkCommand(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmarkCommand(arguments);

        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadlineCommand(arguments);

        case AddEventCommand.COMMAND_WORD:
            return prepareAddEventCommand(arguments);

        case AddToDoCommand.COMMAND_WORD:
            return prepareAddToDoCommand(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(arguments);

        case DueCommand.COMMAND_WORD:
            return prepareDueCommand(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new InvalidCommand();
        }
    }

    /**
     * Processes arguments for commands that require the task number which are
     * delete, mark and unmark.
     *
     * @param args Command arguments
     * @return taskNumber
     * @throws DukeException When the task number is empty.
     */
    private int parseArgsAsTaskNumber(String args) throws DukeException {
        Matcher matcher = TASK_NUMBER_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException(DukeExceptionType.NO_TASK_NUMBER);
        }
        return Integer.parseInt(matcher.group("taskNumber"));
    }

    /**
     * Processes arguments for tasks with deadline which are the description, and the
     * deadline itself.
     *
     * @param args Deadline arguments
     * @return Deadline
     * @throws DukeException When the deadline description is empty or the argument format is wrong.
     */
    public Deadline parseArgsAsDeadline(String args) throws DukeException {
        Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException(DukeExceptionType.INVALID_DEADLINE_FORMAT);
        }

        String deadlineDescription = matcher.group("description").trim();
        String dateString = matcher.group("deadline").trim();

        if (deadlineDescription.isEmpty()) {
            throw new DukeException(DukeExceptionType.DEADLINE_NO_DESCRIPTION);
        }

        LocalDateTime localDateTime = LocalDateTime.parse(dateString, DATE_TIME_FORMAT);

        return new Deadline(deadlineDescription, localDateTime);
    }

    /**
     * Processes arguments for events which are the description, start date / time and end date / time.
     *
     * @param args Event arguments
     * @return Event
     * @throws DukeException When the event description is empty, the argument format is wrong or
     *                       from date is after the to date.
     */
    public Event parseArgsAsEvent(String args) throws DukeException {
        Matcher matcher = EVENT_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException(DukeExceptionType.INVALID_EVENT_FORMAT);
        }

        String eventDescription = matcher.group("description").trim();
        String fromDateString  = matcher.group("fromDate").trim();
        String toDateString  = matcher.group("toDate").trim();

        if (eventDescription.isEmpty()) {
            throw new DukeException(DukeExceptionType.EVENT_NO_DESCRIPTION);
        }

        LocalDateTime fromDate = LocalDateTime.parse(fromDateString, DATE_TIME_FORMAT);
        LocalDateTime toDate = LocalDateTime.parse(toDateString, DATE_TIME_FORMAT);

        if (fromDate.isAfter(toDate)) {
            throw new DukeException(DukeExceptionType.INVALID_EVENT_ARGUMENT);
        }

        return new Event(eventDescription, fromDate, toDate);
    }

    private ToDo parseArgsAsToDo(String args) throws DukeException {
        String description = args.trim();

        if (description.isEmpty()) {
            throw new DukeException(DukeExceptionType.TODO_NO_DESCRIPTION);
        }

        return new ToDo(description);
    }

    /**
     * Helper function to create an instance of a MarkCommand.
     *
     * @param args MarkCommand arguments
     * @return MarkCommand
     * @throws DukeException When the task number is empty.
     */
    private Command prepareMarkCommand(String args) throws DukeException {
        int taskIndex = parseArgsAsTaskNumber(args);
        return new MarkCommand(taskIndex);
    }

    /**
     * Helper function to create an instance of an UnmarkCommand.
     *
     * @param args UnmarkCommand arguments
     * @return UnmarkCommand
     * @throws DukeException When the task number is empty
     */
    private Command prepareUnmarkCommand(String args) throws DukeException {
        int taskIndex = parseArgsAsTaskNumber(args);
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Helper function to create an instance of an AddDeadlineCommand.
     *
     * @param args AddDeadlineCommand arguments
     * @return AddDeadlineCommand
     * @throws DukeException When the deadline description is empty or when the argument format is wrong.
     */
    private Command prepareAddDeadlineCommand(String args) throws DukeException {
        Deadline deadline = parseArgsAsDeadline(args);
        return new AddDeadlineCommand(deadline);
    }

    /**
     * Helper function to create an instance of an AddEventCommand.
     *
     * @param args AddEventCommand arguments
     * @return AddEventCommand
     * @throws DukeException When the event description is empty or when the argument format is wrong.
     */
    private Command prepareAddEventCommand(String args) throws DukeException {
        Event event = parseArgsAsEvent(args);
        return new AddEventCommand(event);
    }

    /**
     * Helper function to create an instance of an AddToDoCommand.
     *
     * @param args AddToDoCommand arguments
     * @return AddToDoCommand
     */
    private Command prepareAddToDoCommand(String args) throws DukeException {
        ToDo todo = parseArgsAsToDo(args);
        return new AddToDoCommand(todo);
    }

    /**
     * Helper function to create an instance of a DeleteCommand
     *
     * @param args DeleteCommand arguments
     * @return DeleteCommand
     * @throws DukeException When the task number is out of range.
     */
    private Command prepareDeleteCommand(String args) throws DukeException {
        int taskIndex = parseArgsAsTaskNumber(args);
        return new DeleteCommand(taskIndex);
    }

    /**
     * Helper function to create an instance of a DueCommand.
     *
     * @param args DueCommand arguments
     * @return DueCommand
     */
    private Command prepareDueCommand(String args) throws DukeException {
        if (args.isEmpty()) {
            throw new DukeException(DukeExceptionType.DUE_NO_DATE);
        }
        LocalDate dueDate = LocalDate.parse(args.trim(), DATE_FORMAT);
        return new DueCommand(dueDate);
    }

    /**
     * Helper function for creating an instance of a FindCommand.
     *
     * @param args FindCommand arguments
     * @return FindCommand
     * @throws DukeException When there is no keyword specified.
     */
    private Command prepareFindCommand(String args) throws DukeException {
        String keyword = args.trim();
        if (keyword.isEmpty()) {
            throw new DukeException(DukeExceptionType.FIND_NO_KEYWORD);
        }
        return new FindCommand(keyword);
    }
}