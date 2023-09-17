package helper;

import java.time.LocalDate;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.DueCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.RemindCommand;
import command.UnmarkCommand;
import exception.InvalidCommandException;
import exception.MissingIndexException;
import exception.MissingTaskException;
import exception.WrongUseOfCommandException;

/**
 * Represents a Parser that takes in the User input and returns a Command object.
 */
public class Parser {
    /**
     * Takes in the input given by the User and wraps it in a Command based on the command specified.
     * @param fullCommand
     * @return a Command
     * @throws WrongUseOfCommandException
     * @throws MissingIndexException
     * @throws InvalidCommandException
     * @throws MissingTaskException
     */
    public static Command parse(String fullCommand) throws WrongUseOfCommandException, MissingIndexException,
            InvalidCommandException, MissingTaskException {
        fullCommand = fullCommand.trim();

        // command is bye or list
        if (fullCommand.startsWith("bye") || fullCommand.startsWith("list")
                || fullCommand.startsWith("remind")) {
            return handleIndexlessCommand(fullCommand);
        }

        // command is mark, unmark, delete, due or find
        if (fullCommand.startsWith("mark") || fullCommand.startsWith("unmark")
                || fullCommand.startsWith("delete") || fullCommand.startsWith("due")
                || fullCommand.startsWith("find")) {
            Command res = handleIndexCommands(fullCommand);
            if (res != null) {
                return res;
            }
        }

        // command is adding new Task
        if (fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") || fullCommand.startsWith("event")) {
            if (fullCommand.equals("todo") || fullCommand.equals("deadline") || fullCommand.equals("event")) {
                throw new MissingTaskException(fullCommand);
            }

            return new AddCommand(-1, fullCommand);
        }

        throw new InvalidCommandException(fullCommand);
    }

    /**
     * Parses all commands with an Index trailing behind.
     * @param fullCommand
     * @return Command to execute at Index
     * @throws MissingIndexException
     */
    private static Command handleIndexCommands(String fullCommand) throws MissingIndexException {
        try {
            String[] res = fullCommand.split(" ", 2);
            String taskType = res[0].strip();

            if (taskType.equals(DueCommand.COMMAND_WORD)) {
                return new DueCommand(-1, LocalDate.parse(res[1].strip()));
            }

            if (taskType.equals(FindCommand.COMMAND_WORD)) {
                return new FindCommand(-1, res[1].strip());
            }

            int index = Integer.parseInt(res[1].strip());

            switch (taskType) {
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(index);
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(index);
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(index);
            default:
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingIndexException(fullCommand);
        }

        return null;
    }

    /**
     * Parses all commands without an Index specified.
     * @param fullCommand
     * @return Command to execute
     * @throws WrongUseOfCommandException
     */
    private static Command handleIndexlessCommand(String fullCommand) throws WrongUseOfCommandException {
        if (fullCommand.equals(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand(-1);
        }

        if (fullCommand.equals(ListCommand.COMMAND_WORD)) {
            return new ListCommand(-1);
        }

        if (fullCommand.equals(RemindCommand.COMMAND_WORD)) {
            return new RemindCommand(-1);
        }

        throw new WrongUseOfCommandException();
    }
}
