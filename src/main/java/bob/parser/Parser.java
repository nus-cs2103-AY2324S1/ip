package bob.parser;

import bob.data.command.ByeCommand;
import bob.data.command.Command;
import bob.data.command.DeadlineCommand;
import bob.data.command.DeleteCommand;
import bob.data.command.EventCommand;
import bob.data.command.FindCommand;
import bob.data.command.ListCommand;
import bob.data.command.MarkCommand;
import bob.data.command.TodoCommand;
import bob.data.command.UnmarkCommand;
import bob.data.command.UpdateCommand;
import bob.data.exception.DukeException;

/**
 * Represents a Parser class that parses the input commands given by the user.
 */
public class Parser {
    private static final String INPUT_TASK_NUMBER = "Input the task number";
    private static final String INVALID_COMMAND = "Invalid command";
    /**
     * Commands to be run based on the user's input.
     */
    public static enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT,
        UPDATE
    }

    /**
     * Returns a command to be executed given a specified input provided by the user.
     *
     * @param input The input of the user.
     * @return A command to be executed which is based on the user input.
     * @throws DukeException If the first word of the input is not a valid command.
     */
    public static Command parse(String input, boolean isUpdate) throws DukeException {
        CommandType commandType;
        if (isUpdate) {
            commandType = getTaskCommandType(input);
        } else {
            commandType = getCommandType(input);
        }
        return validateCommand(commandType, input);
    }

    private static CommandType getCommandType(String input) throws DukeException {
        if (input.equals("bye")) {
            return CommandType.BYE;
        }
        if (input.equals("list")) {
            return CommandType.LIST;
        }
        if (input.startsWith("mark")) {
            return CommandType.MARK;
        }
        if (input.startsWith("unmark")) {
            return CommandType.UNMARK;
        }
        if (input.startsWith("delete")) {
            return CommandType.DELETE;
        }
        if (input.startsWith("find")) {
            return CommandType.FIND;
        }
        if (input.startsWith("todo")) {
            return CommandType.TODO;
        }
        if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        }
        if (input.startsWith("event")) {
            return CommandType.EVENT;
        }
        if (input.startsWith("update")) {
            return CommandType.UPDATE;
        }
        throw new DukeException("No such command.");
    }

    private static CommandType getTaskCommandType(String input) throws DukeException {
        if (input.startsWith("todo")) {
            return CommandType.TODO;
        }
        if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        }
        if (input.startsWith("event")) {
            return CommandType.EVENT;
        }
        throw new DukeException("Not a valid task type.");
    }

    /**
     * Validates what the user types by ensuring the proper format for each command type is adhered to.
     * @param commandType The type of command to check the format against.
     * @param input The user input.
     * @return A Command that when executed, will act as specified by the user.
     * @throws DukeException
     */
    private static Command validateCommand(CommandType commandType, String input) throws DukeException {
        assert input != null : "input should not be null";
        assert commandType != null : "command should not be null";
        int commandWordCount = input.split(" ").length;
        switch(commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            if (commandWordCount == 1) {
                throw new DukeException(INPUT_TASK_NUMBER);
            }
            if (commandWordCount > 2) {
                throw new DukeException(INVALID_COMMAND);
            }
            if (!Parser.isNumber(input.split(" ")[1])) {
                throw new DukeException(INVALID_COMMAND);
            }
            return new MarkCommand(input);
        case UNMARK:
            if (commandWordCount == 1) {
                throw new DukeException(INPUT_TASK_NUMBER);
            }
            if (commandWordCount > 2) {
                throw new DukeException(INVALID_COMMAND);
            }
            if (!Parser.isNumber(input.split(" ")[1])) {
                throw new DukeException(INVALID_COMMAND);
            }
            return new UnmarkCommand(input);
        case DELETE:
            if (commandWordCount == 1) {
                throw new DukeException(INPUT_TASK_NUMBER);
            }
            if (commandWordCount > 2) {
                throw new DukeException(INVALID_COMMAND);
            }
            if (!Parser.isNumber(input.split(" ")[1])) {
                throw new DukeException(INVALID_COMMAND);
            }
            return new DeleteCommand(input);
        case FIND:
            if (input.length() == 4) {
                throw new DukeException("Input something to search for.");
            }
            return new FindCommand(input);
        case TODO:
            if (input.length() == 4 || commandWordCount == 1) {
                throw new DukeException("Description of a todo cannot be empty");
            }
            return new TodoCommand(input);
        case DEADLINE:
            if (input.length() == 8 || commandWordCount == 1) {
                throw new DukeException("Description of a deadline cannot be empty");
            }
            return new DeadlineCommand(input);
        case EVENT:
            if (input.length() == 5 || commandWordCount == 1) {
                throw new DukeException("Description of an event cannot be empty");
            }
            return new EventCommand(input);
        case UPDATE:
            if (input.length() == 6 || commandWordCount == 1) {
                throw new DukeException("Input the task you would like to update.");
            }
            // length = char length of "update " and task number
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            int updateCommandLength = 7 + input.split(" ")[1].length();
            String newTaskDescription = input.substring(updateCommandLength + 1);
            Command newTask = parse(newTaskDescription, true);
            return new UpdateCommand(taskNumber, newTask);
        default:
            throw new DukeException("No such command.");
        }
    }

    private static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
