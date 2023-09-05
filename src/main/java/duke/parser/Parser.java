package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TaskCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeNotTaskException;

/**
 * A Class that parse the command input.
 *
 * @author marioalvaro
 */
public class Parser {
    /**
     * Enum for all the possible command words
     */
    enum CommandWord {
        BYE,
        DELETE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
    }

    /**
     * Method that parse the command input into different Command
     * @param input A String of command
     * @return A Command object that correlate to the input
     * @throws Exception thrown if the command is unknown
     */
    public static Command parse(String input) throws Exception {
        try {
            CommandWord commandWord = CommandWord.valueOf(input.split(" ")[0].toUpperCase());

            String[] splitTask = input.split(" ", 2);
            switch (commandWord) {
            case BYE:
                return new ByeCommand();
            case DELETE:
                if (splitTask.length == 2) {
                    return new DeleteCommand(splitTask);
                }
                throw new DukeNotTaskException("");
            case LIST:
                return new ListCommand();
            case MARK:
                if (splitTask.length == 2) {
                    return new MarkCommand(splitTask);
                }
                throw new DukeNotTaskException("");
            case UNMARK:
                if (splitTask.length == 2) {
                    return new UnmarkCommand(splitTask);
                }
                throw new DukeNotTaskException("");
            case TODO:
            case EVENT:
            case DEADLINE:
                if (splitTask.length == 2) {
                    return new TaskCommand(splitTask);
                }
                throw new DukeNotTaskException("");
            case FIND:
                if (splitTask.length == 2) {
                    return new FindCommand(splitTask);
                }
                throw new DukeNotTaskException("");
            default:
                throw new DukeNotTaskException("");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeNotTaskException(e.getMessage());
        }
    }
}
