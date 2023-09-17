package bob.parser;

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
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Returns a command to be executed given a specified input provided by the user.
     *
     * @param input The input of the user.
     * @return A command to be exectued which is based on the user input.
     * @throws DukeException If the first word of the input is not a valid command.
     */
    public Command parse(String input) throws DukeException {
        assert input != null : "input should not be null";
        String[] inputSplit = input.split(" ");
        Command command = this.getCommand(input);
        assert command != null : "command should not be null";
        switch(command) {
        case BYE:
            return Command.BYE;
        case LIST:
            return Command.LIST;
        case MARK:
            if (inputSplit.length == 1) {
                throw new DukeException(INPUT_TASK_NUMBER);
            } else if (inputSplit.length > 2) {
                throw new DukeException(INVALID_COMMAND);
            } else {
                return Command.MARK;
            }
        case UNMARK:
            if (inputSplit.length == 1) {
                throw new DukeException(INPUT_TASK_NUMBER);
            } else if (inputSplit.length > 2) {
                throw new DukeException(INVALID_COMMAND);
            } else {
                return Command.UNMARK;
            }
        case DELETE:
            if (inputSplit.length == 1) {
                throw new DukeException(INPUT_TASK_NUMBER);
            } else if (inputSplit.length > 2) {
                throw new DukeException(INVALID_COMMAND);
            } else {
                return Command.DELETE;
            }
        case FIND:
            if (input.length() == 4) {
                throw new DukeException("Input something to search for.");
            }
            return Command.FIND;
        case TODO:
            if (input.length() == 4 || inputSplit.length == 1) {
                throw new DukeException("Description of a todo cannot be empty");
            }
            return Command.TODO;
        case DEADLINE:
            if (input.length() == 8 || inputSplit.length == 1) {
                throw new DukeException("Description of a deadline cannot be empty");
            }
            return Command.DEADLINE;
        case EVENT:
            if (input.length() == 5 || inputSplit.length == 1) {
                throw new DukeException("Description of an event cannot be empty");
            }
            return Command.EVENT;
        default:
            throw new DukeException("No such command.");
        }
    }

    private Command getCommand(String input) throws DukeException {
        assert input != null : "input should not be null";
        if (input.equals("bye")) {
            return Command.BYE;
        }
        if (input.equals("list")) {
            return Command.LIST;
        }
        if (input.startsWith("mark")) {
            return Command.MARK;
        }
        if (input.startsWith("unmark")) {
            return Command.UNMARK;
        }
        if (input.startsWith("delete")) {
            return Command.DELETE;
        }
        if (input.startsWith("find")) {
            return Command.FIND;
        }
        if (input.startsWith("todo")) {
            return Command.TODO;
        }
        if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        }
        if (input.startsWith("event")) {
            return Command.EVENT;
        }
        throw new DukeException("I don't understand your command.");
    }
}
