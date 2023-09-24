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
    private static final String INVALID_TASK_NUMBER = "The task number was wrongly input.";
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
     * @throws DukeException If the input is not a valid command.
     */
    public static Command parse(String input, boolean isUpdate) throws DukeException {
        CommandType commandType;
        if (isUpdate) {
            commandType = Parser.getTaskCommandType(input);
        } else {
            commandType = Parser.getCommandType(input);
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
            return Parser.parseByeCommand();
        case LIST:
            return Parser.parseListCommand();
        case MARK:
            return Parser.parseMarkCommand(input);
        case UNMARK:
            return Parser.parseUnmarkCommand(input);
        case DELETE:
            return Parser.parseDeleteCommand(input);
        case FIND:
            return Parser.parseFindCommand(input);
        case TODO:
            return Parser.parseTodoCommand(input);
        case DEADLINE:
            return Parser.parseDeadlineCommand(input);
        case EVENT:
            return Parser.parseEventCommand(input);
        case UPDATE:
            return Parser.parseUpdateCommand(input);
        default:
            throw new DukeException("No such command.");
        }
    }

    private static Command parseByeCommand() {
        return new ByeCommand();
    }

    private static Command parseListCommand() {
        return new ListCommand();
    }

    private static Command parseMarkCommand(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        int commandWordCount = inputArray.length;
        if (!inputArray[0].equals("mark")) {
            throw new DukeException(INVALID_COMMAND);
        }
        if (commandWordCount < 2) {
            throw new DukeException(INPUT_TASK_NUMBER);
        }
        if (commandWordCount > 2) {
            throw new DukeException(INVALID_COMMAND);
        }
        try {
            int taskNumber = Integer.parseInt(inputArray[1].trim());
            return new MarkCommand(inputArray[0] + " " + taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_TASK_NUMBER);
        }
    }

    private static Command parseUnmarkCommand(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        int commandWordCount = inputArray.length;
        if (!inputArray[0].equals("unmark")) {
            throw new DukeException(INVALID_COMMAND);
        }
        if (commandWordCount < 2) {
            throw new DukeException(INPUT_TASK_NUMBER);
        }
        if (commandWordCount > 2) {
            throw new DukeException(INVALID_COMMAND);
        }
        try {
            int taskNumber = Integer.parseInt(inputArray[1].trim());
            return new UnmarkCommand(inputArray[0] + " " + taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_TASK_NUMBER);
        }
    }

    private static Command parseDeleteCommand(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        int commandWordCount = inputArray.length;
        if (!inputArray[0].equals("delete")) {
            throw new DukeException(INVALID_COMMAND);
        }
        if (commandWordCount < 2) {
            throw new DukeException(INPUT_TASK_NUMBER);
        }
        if (commandWordCount > 2) {
            throw new DukeException(INVALID_COMMAND);
        }
        try {
            int taskNumber = Integer.parseInt(inputArray[1].trim());
            return new DeleteCommand(inputArray[0] + " " + taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_TASK_NUMBER);
        }
    }

    private static Command parseFindCommand(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        if (!inputArray[0].equals("find")) {
            throw new DukeException(INVALID_COMMAND);
        }
        int commandWordCount = inputArray.length;
        if (commandWordCount < 2) {
            throw new DukeException("Input something to search for.");
        }
        return new FindCommand(input);
    }

    private static Command parseTodoCommand(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        if (!inputArray[0].equals("todo")) {
            throw new DukeException(INVALID_COMMAND);
        }
        int commandWordCount = inputArray.length;
        if (commandWordCount < 2) {
            throw new DukeException("Description of a todo cannot be empty.");
        }
        return new TodoCommand(input);
    }

    private static Command parseDeadlineCommand(String input) throws DukeException {
        if (!input.split(" ")[0].equals("deadline")) {
            throw new DukeException(INVALID_COMMAND);
        }
        String[] inputArray = input.split("/by");
        int commandWordCount = inputArray.length;
        if (commandWordCount < 2) {
            throw new DukeException("Description of a deadline cannot be empty.");
        }
        if (!input.contains("/by")) {
            throw new DukeException("Deadline task should be in the format: deadline <description> /by dd/MM/yy HHmm");
        }
        String taskDescription = inputArray[0].trim();
        if (taskDescription.length() == 8) {
            throw new DukeException("Description cannot be empty");
        }
        String deadlineDate = inputArray[1].trim();
        return new DeadlineCommand(taskDescription + " /by " + deadlineDate);
    }

    private static Command parseEventCommand(String input) throws DukeException {
        if (!input.split(" ")[0].equals("event")) {
            throw new DukeException(INVALID_COMMAND);
        }
        if (input.equals("event")) {
            throw new DukeException("Description of an event cannot be empty");
        }
        if (!input.contains("/from") && !input.contains("/to")) {
            throw new DukeException("Event task should be in the format:"
                    + "event <description> /from dd/MM/yy HHmm /to dd/MM/yy HHmm");
        }
        String[] taskDescriptionArray = input.split("/from");
        String[] dateArray = taskDescriptionArray[1].split("/to");
        String taskDescription = taskDescriptionArray[0].trim();
        if (taskDescription.length() == 5) {
            throw new DukeException("Description cannot be empty");
        }
        String fromDate = dateArray[0].trim();
        String toDate = dateArray[1].trim();
        return new EventCommand(taskDescription + " /from " + fromDate + " /to " + toDate);
    }

    private static Command parseUpdateCommand(String input) throws DukeException {
        if (!input.split(" ")[0].equals("update")) {
            throw new DukeException(INVALID_COMMAND);
        }
        String[] inputArray = input.split(" ");
        if (input.length() == 6 || inputArray.length == 1) {
            throw new DukeException("Input the task you would like to update.");
        }
        // length = char length of "update " and task number
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        int updateCommandLength = 7 + input.split(" ")[1].length();
        String newTaskDescription = input.substring(updateCommandLength + 1);
        Command newTask = parse(newTaskDescription, true);
        return new UpdateCommand(taskNumber, newTask);
    }
}
