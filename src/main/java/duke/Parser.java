package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.DukeIllegalArgumentsException;
import duke.exception.DukeUnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Timed;
import duke.task.Todo;

/**
 * Represents a parser for the Duke chat-bot that parses the user input and returns the corresponding command.
 */
public class Parser {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param fullCommand The user input.
     * @return The corresponding command.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] splitInput = fullCommand.split(" ", 2);
        String commandWord = splitInput[0];
        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(splitInput[1]) - 1);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(splitInput[1]) - 1);
        case "todo":
            return createTodo(splitInput);
        case "timed":
            return createTimed(splitInput, fullCommand);
        case "deadline":
            return createDeadline(splitInput, fullCommand);
        case "event":
            return createEvent(splitInput, fullCommand);
        case "delete":
            return new DeleteCommand(Integer.parseInt(splitInput[1]) - 1);
        case "find":
            return createFind(splitInput);
        default:
            throw new DukeUnknownCommandException();
        }
    }

    private static AddCommand createTodo(String[] splitInput) throws DukeIllegalArgumentsException {
        handleMissingArguments(splitInput, "The description of a todo cannot be empty");

        return new AddCommand(new Todo(splitInput[1].trim()));
    }

    private static AddCommand createTimed(String[] splitInput, String fullCommand)
            throws DukeIllegalArgumentsException {
        handleMissingArguments(splitInput, "The description of a timed task cannot be empty!");

        String[] splitInputDuration = fullCommand.split("/duration", 2);
        String[] splitTimedDescription = splitInputDuration[0].split(" ", 2);

        handleMissingArguments(splitTimedDescription, "The description of a timed task cannot be empty");
        handleMissingArguments(splitInputDuration,
                "The duration of the timed task must be specified! (after /duration)");

        try {
            return new AddCommand(new Timed(splitTimedDescription[1].trim(),
                    Float.parseFloat(splitInputDuration[1].trim())));
        } catch (NumberFormatException e) {
            throw new DukeIllegalArgumentsException(
                    "The duration provided must be a number (no characters other than \".\")\n");
        }
    }

    private static AddCommand createDeadline(String[] splitInput, String fullCommand)
            throws DukeIllegalArgumentsException {
        handleMissingArguments(splitInput, "The description of a deadline cannot be empty");

        String[] splitInputBy = fullCommand.split("/by", 2);
        String[] splitDeadlineDescription = splitInputBy[0].split(" ", 2);

        handleMissingArguments(splitDeadlineDescription, "The description of a deadline cannot be empty");
        handleMissingArguments(splitInputBy, "The deadline date must be specified! (after /by)");

        try {
            return new AddCommand(new Deadline(splitDeadlineDescription[1].trim(),
                    LocalDateTime.parse(splitInputBy[1].trim(), dateTimeFormat)));
        } catch (DateTimeParseException e) {
            throw new DukeIllegalArgumentsException(
                    "The deadline date provided must be in the format: dd/mm/yyyy HHmm (in 24h format)\n");
        }
    }

    private static AddCommand createEvent(String[] splitInput, String fullCommand)
            throws DukeIllegalArgumentsException {
        handleMissingArguments(splitInput, "The description of an event cannot be empty");

        String[] splitInputFrom = fullCommand.split("/from", 2);
        String[] splitEventDescription = splitInputFrom[0].split(" ", 2);

        handleMissingArguments(splitEventDescription, "The description of an event cannot be empty");
        handleMissingArguments(splitInputFrom,
                "The start time of the event must be specified! (after /from)");

        String[] splitInputTo = splitInputFrom[1].split("/to", 2);

        handleMissingArguments(splitInputTo, "The end time of the event must be specified! (after /to)");

        try {
            return new AddCommand(new Event(splitInputFrom[0].trim(),
                    LocalDateTime.parse(splitInputTo[0].trim(), dateTimeFormat),
                    LocalDateTime.parse(splitInputTo[1].trim(), dateTimeFormat)));
        } catch (DateTimeParseException e) {
            throw new DukeIllegalArgumentsException(
                    "The event dates provided must be in the format: dd/mm/yyyy HHmm (in 24h format)\n");
        }
    }

    private static FindCommand createFind(String[] splitInput) throws DukeIllegalArgumentsException {
        handleMissingArguments(splitInput, "The keyword to search for cannot be empty!");

        return new FindCommand(splitInput[1].trim());
    }

    private static void handleMissingArguments(String[] splitInput, String message)
            throws DukeIllegalArgumentsException {
        if (splitInput.length == 1) {
            throw new DukeIllegalArgumentsException(message + "\n");
        }
        assert splitInput.length == 2 : message;
    }
}
