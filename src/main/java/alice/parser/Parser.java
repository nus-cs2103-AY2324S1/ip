package alice.parser;

import alice.command.AddCommand;
import alice.command.Command;
import alice.command.DeleteCommand;
import alice.command.ExitCommand;
import alice.command.FindCommand;
import alice.command.ListCommand;
import alice.command.MarkAsDoneCommand;
import alice.command.TagCommand;
import alice.command.UnmarkAsDoneCommand;
import alice.command.UntagCommand;
import alice.exception.DukeException;
import alice.task.Task;

/**
 * Represents a parser that parses user input into specific commands.
 */
public class Parser {
    /**
     * Parses the user input into a specific command.
     *
     * @param fullCommand The entire user input.
     * @return The command parsed from the user input.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.split(" ", 2);

        String command = inputs[0].toUpperCase();
        String argument = inputs.length == 2 ? inputs[1] : "";

        try {
            switch (Command.CommandKeyword.valueOf(command)) {
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkAsDoneCommand(argument);
            case UNMARK:
                return new UnmarkAsDoneCommand(argument);
            case DELETE:
                return new DeleteCommand(argument);
            case BYE:
                return new ExitCommand();
            case TODO:
                return new AddCommand(Task.TaskType.TODO, argument);
            case DEADLINE:
                return new AddCommand(Task.TaskType.DEADLINE, argument);
            case EVENT:
                return new AddCommand(Task.TaskType.EVENT, argument);
            case FIND:
                return new FindCommand(argument);
            case TAG:
                return new TagCommand(argument);
            case UNTAG:
                return new UntagCommand(argument);
            default:
                return new Command();
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException(Command.UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }
}
