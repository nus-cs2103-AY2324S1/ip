package corgi.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    private final CommandValidator validator;

    public CommandParser() {
        this.validator = new CommandValidator();
    }

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
            command = newUndoCommand(fullCommand);
            break;
        case BYE:
            command = newExitCommand(fullCommand);
            break;
        case LIST:
            command = newListCommand(fullCommand);
            break;
        case MARK:
            command = newMarkCommand(fullCommand);
            break;
        case UNMARK:
            command = newUnMarkCommand(fullCommand);
            break;
        case TODO:
            command = newAddTodoCommand(fullCommand);
            break;
        case DEADLINE:
            command = newAddDeadlineCommand(fullCommand);
            break;
        case EVENT:
            command = newAddEventCommand(fullCommand);
            break;
        case DELETE:
            command = newDeleteCommand(fullCommand);
            break;
        case DATE:
            command = newDateCommand(fullCommand);
            break;
        case FIND:
            command = newFindCommand(fullCommand);
            break;
        default:
            throw new InvalidCommandTypeException("Invalid Command!");
        }

        return command;
    }

    private Command newUndoCommand(String fullCommand) throws InvalidCommandFormatException {
        if (!validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is needed!" + "\n\n"
                    + CommandType.UNDO.getCommandFormat());
        }
        return new UndoCommand();
    }

    private Command newExitCommand(String fullCommand) throws InvalidCommandFormatException {
        if (!validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is needed!" + "\n\n"
                    + CommandType.BYE.getCommandFormat());
        }
        return new ExitCommand();
    }

    private Command newListCommand(String fullCommand) throws InvalidCommandFormatException {
        if (!validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is needed!" + "\n\n"
                    + CommandType.LIST.getCommandFormat());
        }
        return new ListTasksCommand();
    }

    private Command newMarkCommand(String fullCommand) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.MARK;
        String commandFormat = commandType.getCommandFormat();
        Set<String> arguments = commandType.getArgumentsSet();

        if (validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\n\n"
                    + commandFormat);
        }

        // Validate whether all arguments are given
        this.validator.validateArguments(fullCommand, arguments);

        // Parse arguments
        Map<String, String> labelToValue = parseCommandArgs(fullCommand, arguments);
        String targetTaskNumber = labelToValue.get("/target");

        try {
            int index = Integer.parseInt(targetTaskNumber) - 1;
            return new MarkTaskCommand(index, true);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Please provide a valid task number!" + "\n\n"
                    + commandFormat);
        }
    }

    private Command newUnMarkCommand(String fullCommand) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.UNMARK;
        String commandFormat = commandType.getCommandFormat();
        Set<String> arguments = commandType.getArgumentsSet();

        if (validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\n\n"
                    + commandFormat);
        }

        // Validate whether all arguments are given
        this.validator.validateArguments(fullCommand, arguments);

        // Parse arguments
        Map<String, String> labelToValue = parseCommandArgs(fullCommand, arguments);
        String targetTaskNumber = labelToValue.get("/target");

        try {
            int index = Integer.parseInt(targetTaskNumber) - 1;
            return new MarkTaskCommand(index, false);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Please provide a valid task number!" + "\n\n"
                    + commandFormat);
        }
    }

    private Command newDeleteCommand(String fullCommand) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.DELETE;
        String commandFormat = commandType.getCommandFormat();
        Set<String> arguments = commandType.getArgumentsSet();

        if (validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\n\n"
                    + commandFormat);
        }

        // Validate whether all arguments are given
        this.validator.validateArguments(fullCommand, arguments);

        // Parse arguments
        Map<String, String> labelToValue = parseCommandArgs(fullCommand, arguments);
        String targetTaskNumber = labelToValue.get("/target");

        try {
            int index = Integer.parseInt(targetTaskNumber) - 1;
            return new DeleteTaskCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Please provide a valid task number!" + "\n\n"
                    + commandFormat);
        }
    }

    private Command newDateCommand(String fullCommand) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.DATE;
        String commandFormat = commandType.getCommandFormat();
        Set<String> arguments = commandType.getArgumentsSet();

        if (validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\n\n"
                    + commandFormat);
        }

        // Validate whether all arguments are given
        this.validator.validateArguments(fullCommand, arguments);

        // Parse arguments
        Map<String, String> labelToValue = parseCommandArgs(fullCommand, arguments);
        String targetDate = labelToValue.get("/target");

        LocalDate target = null;

        try {
            target = LocalDate.parse(targetDate, Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date format!");
        }

        assert target != null : "Target date cannot be null.";

        return new FindTasksOnDateCommand(target);
    }

    private Command newFindCommand(String fullCommand) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.FIND;
        String commandFormat = commandType.getCommandFormat();
        Set<String> arguments = commandType.getArgumentsSet();

        if (validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\n\n"
                    + commandFormat);
        }

        // Validate whether all arguments are given
        this.validator.validateArguments(fullCommand, arguments);

        // Parse arguments
        Map<String, String> labelToValue = parseCommandArgs(fullCommand, arguments);
        String targetKeyword = labelToValue.get("/target");

        return new FindTasksContainKeywordCommand(targetKeyword);
    }

    private Command newAddTodoCommand(String fullCommand) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.TODO;
        String commandFormat = commandType.getCommandFormat();
        Set<String> arguments = commandType.getArgumentsSet();

        if (validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\n\n"
                    + commandFormat);
        }

        // Validate whether all arguments are given
        this.validator.validateArguments(fullCommand, arguments);

        // Parse arguments
        Map<String, String> labelToValue = parseCommandArgs(fullCommand, arguments);
        String todoDesc = labelToValue.get("/desc");

        Task target = new ToDo(todoDesc);

        assert target != null : "New Todo task cannot be null.";

        return new AddTaskCommand(target);
    }

    private Command newAddDeadlineCommand(String fullCommand) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.DEADLINE;
        String commandFormat = commandType.getCommandFormat();
        Set<String> arguments = commandType.getArgumentsSet();

        if (validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\n\n"
                    + commandFormat);
        }

        // Validate whether all arguments are given
        this.validator.validateArguments(fullCommand, arguments);

        // Parse arguments
        Map<String, String> labelToValue = parseCommandArgs(fullCommand, arguments);
        String deadlineDesc = labelToValue.get("/desc");
        String deadlineStr = labelToValue.get("/by");

        LocalDate by = null;

        try {
            by = LocalDate.parse(deadlineStr, Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date format!" + "\n\n"
                    + commandFormat);
        }

        assert by != null : "Date cannot be null.";

        Task target = new Deadline(deadlineDesc, by);

        assert target != null : "New Deadline task cannot be null.";

        return new AddTaskCommand(target);
    }

    private Command newAddEventCommand(String fullCommand) throws InvalidCommandFormatException {
        CommandType commandType = CommandType.EVENT;
        String commandFormat = commandType.getCommandFormat();
        Set<String> arguments = commandType.getArgumentsSet();

        if (validator.hasNoArgument(fullCommand)) {
            throw new InvalidCommandFormatException("No argument is provided!" + "\n"
                    + commandFormat);
        }

        // Validate whether all arguments are given
        this.validator.validateArguments(fullCommand, arguments);

        // Parse arguments
        Map<String, String> labelToValue = parseCommandArgs(fullCommand, arguments);
        String eventDesc = labelToValue.get("/desc");
        String startDateStr = labelToValue.get("/from");
        String endDateStr = labelToValue.get("/to");

        LocalDate from = null;
        LocalDate to = null;

        try {
            from = LocalDate.parse(startDateStr, Task.DATE_INPUT_FORMATTER);
            to = LocalDate.parse(endDateStr, Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandFormatException("Invalid date format!" + "\n\n"
                    + commandFormat);
        }

        // Validate that start date is before end date
        if (!from.isBefore(to)) {
            throw new InvalidCommandFormatException("The start date should be before the end date!");
        }

        assert from != null : "Date cannot be null.";
        assert to != null : "Date cannot be null.";

        Task target = new Event(eventDesc, from, to);

        assert target != null : "New Event task cannot be null.";

        return new AddTaskCommand(target);
    }

    private Map<String, String> parseCommandArgs(String command, Set<String> arguments)
            throws InvalidCommandFormatException {
        String[] splitWithSpace = command.split(" ");
        Map<String, String> argToValue = new HashMap<>();

        String currArg = null;
        int indexOfCurrArg = 0;

        for (int i = 0; i < splitWithSpace.length; i++) {
            String currWord = splitWithSpace[i];

            if (!arguments.contains(currWord)) {
                continue;
            }

            if (currArg != null) {
                if (indexOfCurrArg + 1 == i) {
                    throw new InvalidCommandFormatException("Missing value for argument \"" + currArg + "\"");
                }
                String[] valueList = Arrays.copyOfRange(splitWithSpace, indexOfCurrArg + 1, i);
                String value = String.join(" ", valueList);
                argToValue.put(currArg, value);
            }

            currArg = currWord;
            indexOfCurrArg = i;
        }

        if (currArg != null) {
            if (indexOfCurrArg + 1 == splitWithSpace.length) {
                throw new InvalidCommandFormatException("Missing value for argument \"" + currArg + "\" !");
            }
            String[] valueList = Arrays.copyOfRange(splitWithSpace, indexOfCurrArg + 1, splitWithSpace.length);
            String value = String.join(" ", valueList);
            argToValue.put(currArg, value);
        }

        return argToValue;
    }
}
