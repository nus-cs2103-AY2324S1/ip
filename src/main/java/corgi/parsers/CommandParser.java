package corgi.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import corgi.commands.AddTaskCommand;
import corgi.commands.Command;
import corgi.commands.CommandType;
import corgi.commands.DeleteTaskCommand;
import corgi.commands.ExitCommand;
import corgi.commands.FindTasksContainKeywordCommand;
import corgi.commands.FindTasksOnDateCommand;
import corgi.commands.InvalidCommandException;
import corgi.commands.ListTasksCommand;
import corgi.commands.MarkTaskCommand;
import corgi.commands.UndoCommand;
import corgi.tasks.Deadline;
import corgi.tasks.Event;
import corgi.tasks.Task;
import corgi.tasks.ToDo;

/**
 * A parser class for interpreting user input and generating corresponding Command objects.
 */
public class CommandParser extends Parser<Command> {

    /**
     * Parses the given full command string and generates the corresponding Command object.
     *
     * @param fullCommand The full user input command string.
     * @return The Command object representing the parsed command.
     * @throws InvalidCommandFormatException If the command format is invalid.
     * @throws InvalidCommandTypeException  If the command type is invalid.
     */
    @Override
    public Command parse(String fullCommand) throws InvalidCommandFormatException, InvalidCommandTypeException {
        String[] inputs = fullCommand.split(" ", 2);
        String cmdStr = inputs[0];

        CommandType cmd;

        try {
            cmd = CommandType.getCommandType(cmdStr);
        } catch (InvalidCommandException e) {
            throw new InvalidCommandTypeException("Invalid Command!");
        }

        Command command = null;

        switch (cmd) {
        case UNDO:
            command = newUndoCommand(inputs);
            break;
        case BYE:
            command = newExitCommand(inputs);
            break;
        case LIST:
            command = newListCommand(inputs);
            break;
        case MARK:
            command = newMarkCommand(inputs);
            break;
        case UNMARK:
            command = newUnMarkCommand(inputs);
            break;
        case TODO:
            command = newAddTodoCommand(inputs);
            break;
        case DEADLINE:
            command = newAddDeadlineCommand(inputs);
            break;
        case EVENT:
            command = newAddEventCommand(inputs);
            break;
        case DELETE:
            command = newDeleteCommand(inputs);
            break;
        case DATE:
            command = newDateCommand(inputs);
            break;
        case FIND:
            command = newFindCommand(inputs);
            break;
        default:
            throw new InvalidCommandTypeException("Invalid Command!");
        }

        return command;
    }

    private Command newUndoCommand(String[] inputs) throws InvalidCommandFormatException {
        if (inputs.length > 1) {
            throw new InvalidCommandFormatException("No argument is needed!" + "\nFormat: "
                    + CommandType.UNDO.getCommandFormat());
        }
        return new UndoCommand();
    }

    private Command newExitCommand(String[] inputs) throws InvalidCommandFormatException {
        if (inputs.length > 1) {
            throw new InvalidCommandFormatException("No argument is needed!" + "\nFormat: "
                    + CommandType.BYE.getCommandFormat());
        }
        return new ExitCommand();
    }

    private Command newListCommand(String[] inputs) throws InvalidCommandFormatException {
        if (inputs.length > 1) {
            throw new InvalidCommandFormatException("No argument is needed!" + "\nFormat: "
                    + CommandType.LIST.getCommandFormat());
        }
        return new ListTasksCommand();
    }

    private Command newMarkCommand(String[] inputs) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.MARK;
        String commandFormat = commandType.getCommandFormat();

        if (inputs.length == 1) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\nFormat: "
                    + commandFormat);
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            return new MarkTaskCommand(index, true);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Please provide a valid task number!" + "\nFormat: "
                    + commandFormat);
        }
    }

    private Command newUnMarkCommand(String[] inputs) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.UNMARK;
        String commandFormat = commandType.getCommandFormat();

        if (inputs.length == 1) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\nFormat: "
                    + commandFormat);
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            return new MarkTaskCommand(index, true);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Please provide a valid task number!" + "\nFormat: "
                    + commandFormat);
        }
    }

    private Command newDeleteCommand(String[] inputs) throws InvalidCommandFormatException {
        if (inputs.length == 1) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\nFormat: "
                    + CommandType.DELETE.getCommandFormat());
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;
            return new DeleteTaskCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Please provide a valid task number!" + "\nFormat: "
                    + CommandType.DELETE.getCommandFormat());
        }
    }

    private Command newDateCommand(String[] inputs) throws InvalidCommandFormatException {
        if (inputs.length == 1) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\nFormat: "
                    + CommandType.DATE.getCommandFormat());
        }

        LocalDate target = null;

        String dateStr = inputs[1];

        try {
            target = LocalDate.parse(dateStr, Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date format!");
        }

        assert target != null : "Target date cannot be null.";

        return new FindTasksOnDateCommand(target);
    }

    private Command newFindCommand(String[] inputs) throws InvalidCommandFormatException {
        if (inputs.length == 1) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\nFormat: "
                    + CommandType.FIND.getCommandFormat());
        }

        String keyword = inputs[1];

        return new FindTasksContainKeywordCommand(keyword);
    }

    private Command newAddTodoCommand(String[] inputs) throws InvalidCommandFormatException {
        if (inputs.length == 1) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\nFormat: "
                    + CommandType.TODO.getCommandFormat());
        }

        String taskInfo = inputs[1];

        Task target = new ToDo(taskInfo);

        assert target != null : "New Todo task cannot be null.";

        return new AddTaskCommand(target);
    }

    private Command newAddDeadlineCommand(String[] inputs) throws InvalidCommandFormatException {
        String commandFormat = CommandType.DEADLINE.getCommandFormat();

        if (inputs.length == 1) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\nFormat: "
                    + commandFormat);
        }

        String taskInfo = inputs[1];

        // todo: check number of /by
        String[] deadlineInfos = taskInfo.split(" /by ");

        if (deadlineInfos.length == 1) {
            throw new InvalidCommandFormatException("Missing deadline!" + "\nFormat: "
                    + commandFormat);
        }

        String deadlineDesc = deadlineInfos[0];
        LocalDate by = null;

        try {
            by = LocalDate.parse(deadlineInfos[1], Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date format!" + "\nFormat: "
                    + commandFormat);
        }

        assert by != null : "Date cannot be null.";

        Task target = new Deadline(deadlineDesc, by);

        assert target != null : "New Deadline task cannot be null.";

        return new AddTaskCommand(target);
    }

    private Command newAddEventCommand(String[] inputs) throws InvalidCommandFormatException {
        String commandFormat = CommandType.EVENT.getCommandFormat();

        if (inputs.length == 1) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\nFormat: "
                    + commandFormat);
        }

        String taskInfo = inputs[1];

        // todo: check number of /from, /to, check order
        String[] eventInfos = taskInfo.split(" /from ");

        if (eventInfos.length < 2) {
            throw new InvalidCommandFormatException("Missing /from argument." + "\nFormat: "
                    + commandFormat);
        } else if (eventInfos.length > 2) {
            throw new InvalidCommandFormatException("Only one /from argument is needed." + "\nFormat: "
                    + commandFormat);
        }

        String eventDesc = eventInfos[0];
        String[] eventDuration = eventInfos[1].split(" /to ");

        if (eventDuration.length < 2) {
            throw new InvalidCommandFormatException("Missing /to argument!" + "\nFormat: "
                    + commandFormat);
        } else if (eventDuration.length > 2) {
            throw new InvalidCommandFormatException("Only one /to argument is needed." + "\nFormat: "
                    + commandFormat);
        }

        LocalDate from = null;
        LocalDate to = null;

        try {
            from = LocalDate.parse(eventDuration[0], Task.DATE_INPUT_FORMATTER);
            to = LocalDate.parse(eventDuration[1], Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date format!" + "\nFormat: "
                    + commandFormat);
        }

        assert from != null : "Date cannot be null.";
        assert to != null : "Date cannot be null.";

        Task target = new Event(eventDesc, from, to);

        assert target != null : "New Event task cannot be null.";

        return new AddTaskCommand(target);
    }
}
