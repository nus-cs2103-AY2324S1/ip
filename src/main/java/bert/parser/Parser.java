package bert.parser;

import bert.commands.AddDeadlineCommand;
import bert.commands.AddEventCommand;
import bert.commands.AddToDoCommand;
import bert.commands.Command;
import bert.commands.DeleteCommand;
import bert.commands.ExitCommand;
import bert.commands.ListCommand;
import bert.commands.MarkCommand;
import bert.commands.UnmarkCommand;
import bert.exceptions.BertEmptyTaskException;
import bert.exceptions.BertInvalidTaskException;

import java.time.LocalDate;

public class Parser {
    public Command parse(String fullCommand) throws BertInvalidTaskException, BertEmptyTaskException {
        String command;
        String arguments = "";
        int indexOfFirstSpace = fullCommand.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            command = fullCommand;
        } else {
            command = fullCommand.substring(0, indexOfFirstSpace);
            arguments = fullCommand.substring(indexOfFirstSpace + 1);
        }

        switch (command) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);
        case AddToDoCommand.COMMAND_WORD:
            return prepareToDo(arguments);
        case AddDeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case AddEventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new BertInvalidTaskException();
        }
    }

    private Command prepareMark(String arguments) {
        int index = Integer.parseInt(arguments) - 1;
        return new MarkCommand(index);
    }

    private Command prepareUnmark(String arguments) {
        int index = Integer.parseInt(arguments) - 1;
        return new UnmarkCommand(index);
    }

    private Command prepareToDo(String arguments) throws BertEmptyTaskException {
        if (arguments.isBlank()) {
            throw new BertEmptyTaskException();
        }
        return new AddToDoCommand(arguments);
    }

    private Command prepareDeadline(String arguments) throws BertEmptyTaskException {
        if (arguments.isBlank()) {
            throw new BertEmptyTaskException();
        }
        String[] descriptionAndDeadline = arguments.split(" /by ");
        LocalDate deadline = LocalDate.parse(descriptionAndDeadline[1]);
        return new AddDeadlineCommand(descriptionAndDeadline[0], deadline);
    }

    private Command prepareEvent(String arguments) throws BertEmptyTaskException {
        if (arguments.isBlank()) {
            throw new BertEmptyTaskException();
        }
        String[] descriptionAndDates = arguments.split(" /from ");
        String[] dates = descriptionAndDates[1].split(" /to ");
        LocalDate start = LocalDate.parse(dates[0]);
        LocalDate end = LocalDate.parse(dates[1]);
        return new AddEventCommand(descriptionAndDates[0], start, end);
    }

    private Command prepareDelete(String arguments) {
        int index = Integer.parseInt(arguments) - 1;
        return new DeleteCommand(index);
    }
}
