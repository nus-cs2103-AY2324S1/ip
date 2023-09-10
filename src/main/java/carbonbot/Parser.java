package carbonbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import carbonbot.command.AddCommand;
import carbonbot.command.Command;
import carbonbot.command.DeleteCommand;
import carbonbot.command.ExitCommand;
import carbonbot.command.FindCommand;
import carbonbot.command.ListCommand;
import carbonbot.command.MarkCommand;
import carbonbot.exception.CarbonException;
import carbonbot.exception.CarbonInputParseException;
import carbonbot.task.Deadline;
import carbonbot.task.Event;
import carbonbot.task.Todo;

/**
 * The Parser class helps to parse user input strings to return the appropriate Command.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    /**
     * Parses the user input and returns the corresponding Command to be executed
     *
     * @param fullCommand The raw string the user has input
     * @return The Command if the fullCommand string was valid
     */
    public static Command parse(String fullCommand) throws CarbonException {
        String commandType = fullCommand.split(" ")[0];

        switch (commandType) {
        case BYE_COMMAND:
            return new ExitCommand();
        case LIST_COMMAND:
            return new ListCommand();
        case TODO_COMMAND:
            return parseTodoCommand(fullCommand);
        case DEADLINE_COMMAND:
            return parseDeadlineCommand(fullCommand);
        case EVENT_COMMAND:
            return parseEventCommand(fullCommand);
        case MARK_COMMAND:
            return new MarkCommand(getIntegerArgument(fullCommand), true);
        case UNMARK_COMMAND:
            return new MarkCommand(getIntegerArgument(fullCommand), false);
        case DELETE_COMMAND:
            return new DeleteCommand(getIntegerArgument(fullCommand));
        case FIND_COMMAND:
            return parseFindCommand(fullCommand);
        default:
            throw new CarbonInputParseException(":( OOPS!!! I'm sorry, but I don't know what that means :-("
                    + "\nMy supported commands are: list, mark, unmark, todo, deadline, event, find, bye.");
        }
    }

    private static Command parseFindCommand(String fullCommand) {
        String keyword = fullCommand.substring(FIND_COMMAND.length()).trim();
        return new FindCommand(keyword);
    }

    private static Command parseTodoCommand(String fullCommand) throws CarbonInputParseException {
        String description = fullCommand.substring(TODO_COMMAND.length()).trim();
        if (description.isBlank()) {
            throw new CarbonInputParseException("The description cannot be empty.");
        }
        return new AddCommand(new Todo(description));
    }

    private static Command parseDeadlineCommand(String fullCommand) throws CarbonInputParseException {
        int indexOfBy = fullCommand.indexOf("/by");

        // Validates the existence of /by syntax
        if (indexOfBy == -1) {
            throw new CarbonInputParseException("Please specify the deadline using /by.");
        }

        String description = fullCommand.substring(DEADLINE_COMMAND.length(), indexOfBy).trim();
        String by = fullCommand.substring(indexOfBy + "/by".length()).trim();
        if (description.isBlank()) {
            throw new CarbonInputParseException("The description cannot be empty.");
        }
        if (by.isBlank()) {
            throw new CarbonInputParseException("The due date must be specified using /by.");
        }

        try {
            LocalDateTime byDt = parseDateTimeString(by);
            return new AddCommand(new Deadline(description, byDt));
        } catch (DateTimeParseException ex) {
            throw new CarbonInputParseException("The datetime was not in a valid format."
                    + " Example of valid datetime: 26/12/2019 1800");
        }
    }

    private static Command parseEventCommand(String fullCommand) throws CarbonInputParseException {
        int indexOfFrom = fullCommand.indexOf("/from");
        int indexOfTo = fullCommand.indexOf("/to");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new CarbonInputParseException("Please specify the start and end of the"
                    + " event using /from and /to.");
        }
        if (indexOfFrom > indexOfTo) {
            throw new CarbonInputParseException("Please specify the /from before the /to!");
        }

        String description = fullCommand.substring(EVENT_COMMAND.length(), indexOfFrom).trim();
        String from = fullCommand.substring(indexOfFrom + "/from".length(), indexOfTo).trim();
        String to = fullCommand.substring(indexOfTo + "/to".length()).trim();

        if (description.isBlank()) {
            throw new CarbonInputParseException("The description cannot be empty.");
        }
        if (from.isBlank()) {
            throw new CarbonInputParseException("The 'from' of an event cannot be empty.");
        }
        if (to.isBlank()) {
            throw new CarbonInputParseException("The 'to' of an event cannot be empty.");
        }
        try {
            LocalDateTime fromDt = parseDateTimeString(from);
            LocalDateTime toDt = parseDateTimeString(to);
            return new AddCommand(new Event(description, fromDt, toDt));
        } catch (DateTimeParseException ex) {
            throw new CarbonInputParseException("The given datetime was not in a valid format."
                    + " Example of valid datetime: 26/12/2019 1800");
        }
    }

    private static LocalDateTime parseDateTimeString(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private static int getIntegerArgument(String fullCommand) throws CarbonException {
        try {
            return Integer.parseInt(fullCommand.split(" ")[1]);
        } catch (NumberFormatException nfe) {
            throw new CarbonException("Please provide a valid integer for the index.");
        } catch (ArrayIndexOutOfBoundsException aie) {
            throw new CarbonException("No index was provided. Please enter the task index to be updated.");
        }
    }

}
