package skye.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import skye.commands.AddDeadlineCommand;
import skye.commands.AddEventCommand;
import skye.commands.AddToDoCommand;
import skye.commands.AddVenueCommand;
import skye.commands.ByeCommand;
import skye.commands.Command;
import skye.commands.DeleteCommand;
import skye.commands.DeleteTaskCommand;
import skye.commands.DeleteVenueCommand;
import skye.commands.DueCommand;
import skye.commands.FindCommand;
import skye.commands.FindTasksCommand;
import skye.commands.FindVenuesCommand;
import skye.commands.HelpCommand;
import skye.commands.InvalidCommand;
import skye.commands.ListCommand;
import skye.commands.ListTasksCommand;
import skye.commands.ListVenuesCommand;
import skye.commands.MarkCommand;
import skye.commands.UnmarkCommand;
import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.data.task.Deadline;
import skye.data.task.Event;
import skye.data.task.ToDo;
import skye.data.venue.Venue;

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

    public static final Pattern VENUE_ARGS_FORMAT =
            Pattern.compile("(?<name>.*?)\\s+/address\\s+(?<address>.*?)"
                    + "\\s+/size\\s+(?<capacity>.*?)\\s+/rent\\s+(?<rent>.*)");

    public static final Pattern DELETE_SPECIFIC_FORMAT = Pattern.compile("(?<resource>.*?)\\s+/index\\s(?<index>.*)");
    public static final Pattern FIND_SPECIFIC_FORMAT = Pattern.compile("(?<resource>.*?)\\s+/q\\s(?<query>.*)");

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
            return prepareListCommand(arguments);

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

        case AddVenueCommand.COMMAND_WORD:
            return prepareAddVenueCommand(arguments);

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
        assert !dateString.isEmpty();

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
        String fromDateString = matcher.group("fromDate").trim();
        assert !fromDateString.isEmpty();
        String toDateString = matcher.group("toDate").trim();
        assert !toDateString.isEmpty();

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
     * Helper function to create an instance of an AddVenueCommand.
     *
     * @param args AddVenueCommand arguments
     * @return AddVenueCommand
     */
    private Venue parseArgsAsVenue(String args) throws DukeException {
        Matcher matcher = VENUE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException(DukeExceptionType.INVALID_VENUE_FORMAT);
        }

        String name = matcher.group("name").trim();
        String address = matcher.group("address").trim();

        String capacityString = matcher.group("capacity").trim();
        int capacity = Integer.parseInt(capacityString);

        String rentCostString = matcher.group("rent").trim();
        double rent = Double.parseDouble(rentCostString);

        return new Venue(name, address, capacity, rent);
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
     * Helper function to create an instance of an AddToDoCommand.
     *
     * @param args AddVenueCommand arguments
     * @return AddVenueCommand
     */
    private Command prepareAddVenueCommand(String args) throws DukeException {
        Venue venue = parseArgsAsVenue(args);
        return new AddVenueCommand(venue);
    }

    /**
     * Helper function to create an instance of a DeleteCommand
     *
     * @param args DeleteCommand arguments
     * @return DeleteCommand
     * @throws DukeException When the task number is out of range.
     */
    private Command prepareDeleteCommand(String args) throws DukeException {
        Matcher matcher = DELETE_SPECIFIC_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new DukeException(DukeExceptionType.DELETE_INVALID_FORMAT);
        } else {
            String resource = matcher.group("resource").trim();
            int index = parseArgsAsTaskNumber(matcher.group("index").trim());
            switch (resource) {
            case DeleteVenueCommand.RESOURCE:
                return new DeleteVenueCommand(index);
            case DeleteTaskCommand.RESOURCE:
                return new DeleteTaskCommand(index);
            default:
                throw new DukeException(DukeExceptionType.UNKNOWN_RESOURCE_TYPE);
            }
        }
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
        Matcher matcher = FIND_SPECIFIC_FORMAT.matcher(args);
        if (matcher.matches()) {
            String resource = matcher.group("resource").trim();
            String query = matcher.group("query").trim();

            if (query.isEmpty()) {
                throw new DukeException(DukeExceptionType.FIND_NO_KEYWORD);
            }

            switch (resource) {
            case FindVenuesCommand.RESOURCE:
                return new FindVenuesCommand(query);
            case FindTasksCommand.RESOURCE:
                return new FindTasksCommand(query);
            default:
                throw new DukeException(DukeExceptionType.UNKNOWN_RESOURCE_TYPE);
            }
        } else {
            throw new DukeException(DukeExceptionType.FIND_NO_RESOURCE_SPECIFIED);
        }
    }

    private Command prepareListCommand(String arguments) throws DukeException {
        String resource = arguments.trim();
        if (resource.isEmpty()) {
            throw new DukeException(DukeExceptionType.LIST_NO_RESOURCE_SPECIFIED);
        }
        switch (resource) {
        case ListTasksCommand.RESOURCE:
            return new ListTasksCommand();
        case ListVenuesCommand.RESOURCE:
            return new ListVenuesCommand();
        default:
            throw new DukeException(DukeExceptionType.UNKNOWN_RESOURCE_TYPE);
        }
    }
}
