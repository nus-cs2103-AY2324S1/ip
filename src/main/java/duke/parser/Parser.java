package duke.parser;

import duke.command.Command;
import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.RescheduleCommand;
import duke.command.WrongCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for parsing data from standard input for the Duke Program.
 */
public class Parser {

    /**
     * Parses the input given by the user from standard input and packages them into a Command instance.
     *
     * @param input A string representing a full command from the standard input
     * @return A Command Object which holds the necessary data required for further execution of tasks
     * @throws DukeException If any full command is incomplete or cannot be understood
     */
    public static Command parse(String input) throws DukeException {
        input = input.trim();
        String commandWord = input.split(" ", 2)[0];
        switch (commandWord) {
        case AddTodoCommand.COMMAND_WORD:
            return createAddTodoCommand(input);
        case AddEventCommand.COMMAND_WORD:
            return createAddEventCommand(input);
        case AddDeadlineCommand.COMMAND_WORD:
            return createAddDeadlineCommand(input);
        case MarkCommand.COMMAND_WORD:
            return createMarkCommand(input);
        case UnmarkCommand.COMMAND_WORD:
            return createUnmarkCommand(input);
        case DeleteCommand.COMMAND_WORD:
            return createDeleteCommand(input);
        case FindCommand.COMMAND_WORD:
            return createFindCommand(input);
        case RescheduleCommand.COMMAND_WORD:
            return createRescheduleCommand(input);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new WrongCommand();
        }
    }

    private static RescheduleCommand createRescheduleCommand(String input) throws DukeException {
        String[] rescheduleData = input.split(" ", 3);
        if (rescheduleData.length < 3) {
            throw new DukeException("Provide the command as such:\nreschedule [index] [deadline]");
        }
        try {
            String newDeadline = LocalDate.parse(rescheduleData[2]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new RescheduleCommand(Integer.valueOf(rescheduleData[1]), newDeadline);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the index of the task (Numbers only)");
        } catch (DateTimeException e) {
            throw new DukeException("Please represent time in a proper time format of yyyy-mm-dd");
        }
    }

    private static FindCommand createFindCommand(String input) throws DukeException {
        String[] searchData = input.split(" ", 2);
        if (searchData.length < 2) {
            throw new DukeException("Search string cannot be empty");
        }
        return new FindCommand(searchData[1]);
    }

    private static DeleteCommand createDeleteCommand(String input) throws DukeException {
        String[] deleteData = input.split(" ", 2);
        if (deleteData.length < 2) {
            throw new DukeException("Please select a task using its index");
        }
        try {
            return new DeleteCommand(Integer.valueOf(deleteData[1]));
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the index of the task (Numbers only)");
        }
    }

    private static UnmarkCommand createUnmarkCommand(String input) throws DukeException {
        String[] unmarkData = input.split(" ", 2);
        if (unmarkData.length < 2) {
            throw new DukeException("Please select a task using its index");
        }
        try {
            return new UnmarkCommand(Integer.valueOf(unmarkData[1]));
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the index of the task (Numbers only)");
        }
    }

    private static MarkCommand createMarkCommand(String input) throws DukeException {
        String[] markData = input.split(" ", 2);
        if (markData.length < 2) {
            throw new DukeException("Please select a task using its index");
        }
        try {
            return new MarkCommand(Integer.valueOf(markData[1]));
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify the index of the task (Numbers only)");
        }
    }

    private static AddDeadlineCommand createAddDeadlineCommand(String input) throws DukeException {
        String[] deadlineData = input.split(" /by ", 2);
        if (deadlineData.length < 2) {
            throw new DukeException("A deadline task requires a /by (timedate) descriptor");
        }
        if (deadlineData[0].split(" ", 2).length < 2) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        try {
            String by = LocalDate.parse(deadlineData[1]).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return new AddDeadlineCommand(deadlineData[0].split(" ", 2)[1], by);
        } catch (DateTimeException e) {
            throw new DukeException("Please represent time in a proper time format of yyyy-mm-dd");
        }
    }

    private static AddEventCommand createAddEventCommand(String input) throws DukeException {
        String[] eventData = input.split(" /from ", 2);
        if (eventData.length < 2) {
            throw new DukeException("An event requires a /from (timedate) descriptor");
        }
        String[] period = eventData[1].split("/to ");
        if (period.length < 2) {
            throw new DukeException("An event requires a /to (timedate) descriptor");
        }
        if (eventData[0].split(" ", 2).length < 2) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        try {
            String from = LocalDate.parse(period[0].trim())
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String to = LocalDate.parse(period[1].trim())
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            return new AddEventCommand(eventData[0].split(" ", 2)[1], from, to);
        } catch (DateTimeException e) {
            throw new DukeException("Please represent time in a proper time format of yyyy-mm-dd");
        }
    }

    private static AddTodoCommand createAddTodoCommand(String input) throws DukeException {
        String[] todoData = input.split(" ", 2);
        if (todoData.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddTodoCommand(todoData[1]);
    }

}
