package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * The duke.Parser class takes an input and returns
 * a corresponding command.
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Parser {
    // Initialisation of objects and variables
    private enum Commands {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        FIND
    }

    /**
     * Returns a corresponding Command from input.
     *
     * @param fullCommand a String with the command
     * @return A Command to be executed
     * @throws DukeException If input does not match
     */
    public static Command parse(String fullCommand) throws DukeException {
        int index;
        String[] temp = fullCommand.split(" ", 2);
        try {
            Commands command = Commands.valueOf(temp[0].toUpperCase());
            switch (command) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                index = Integer.parseInt(temp[1]);
                return new MarkCommand(index);
            case UNMARK:
                index = Integer.parseInt(temp[1]);
                return new UnmarkCommand(index);
            case FIND:
                return new FindCommand(temp[1]);
            case DELETE:
                index = Integer.parseInt(temp[1]);
                return new DeleteCommand(index);
            case TODO:
                return new AddCommand(new Todo(temp[1]));
            case EVENT:
                return new AddCommand(new Event(temp[1]));
            case DEADLINE:
                return new AddCommand(new Deadline(temp[1]));
            default:
                return new UnknownCommand();
            }
        } catch (IllegalArgumentException e) {
            return new UnknownCommand();
        }
    }
}
