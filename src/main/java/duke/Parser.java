package duke;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.TaskCommand;
import duke.commands.DeleteCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.exceptions.IncompleteDescriptionException;
import duke.exceptions.UnknownCommandException;

/**
 * Parses string input from the user.
 */
public class Parser {

    private enum CommandHeaders {
        BYE,
        MARK,
        UNMARK,
        LIST,
        DELETE,
        FIND,
        TODO,
        DEADLINE,
        EVENT;
    }

    /**
     * Returns a task object parsed from string data stored in the hard disk.
     *
     * @param data The string containing information of the task stored in the hard disk.
     * @return A task object configured accoriding to the information in the data string.
     * @throws IncompleteDescriptionException If data is corrupted.
     */
    public static Task dataToTask(String data) throws IncompleteDescriptionException {
        String[] dataArr = data.split(" \\| ");
        Task t;
        switch (dataArr[0]) {
        case "T":
            if (dataArr.length != 3) throw new IncompleteDescriptionException();
            t = new ToDo(dataArr[2]);
            break;
        case "D":
            if (dataArr.length != 4) throw new IncompleteDescriptionException();
            t = new Deadline(dataArr[2], dataArr[3]);
            break;
        case "E" :
            if (dataArr.length != 5) throw new IncompleteDescriptionException();
            t = new Event(dataArr[2], dataArr[3], dataArr[4]);
            break;
        default:
            throw new IncompleteDescriptionException();
        }
        if (dataArr[1].equals("1")) {
            t.markDone();
        }
        return t;
    }

    /**
     * Parses a string containing the full Command of the user into a command.
     *
     * @param fullCommand The string containing the full command of the user.
     * @return A Command object encapsulating the command of the user.
     * @throws UnknownCommandException If the string fullCommand is unable to be parsed and recognised as a command.
     * @throws IncompleteDescriptionException If the fullCommand is unable to be parsed fully.
     */
    public static Command cmdFromString(String fullCommand) throws UnknownCommandException, IncompleteDescriptionException {
        String[] tmp = fullCommand.split(" ", 2);
        String cmdHdrString = tmp[0];
        String cmdDesc = (tmp.length == 1) ? "" : tmp[1];
        try {
            CommandHeaders cmdHdr = CommandHeaders.valueOf(cmdHdrString.toUpperCase());
            switch (cmdHdr) {
            case BYE:
                return new ExitCommand();
            case MARK:
                return new MarkCommand(Integer.parseInt(cmdDesc));
            case UNMARK:
                return new UnmarkCommand(Integer.parseInt(cmdDesc));
            case LIST:
                return new ListCommand();
            case DELETE:
                return new DeleteCommand(Integer.parseInt(cmdDesc));
            case FIND:
                return new FindCommand(cmdDesc);
            case TODO:
                return new TaskCommand(ToDo.create(cmdDesc));
            case DEADLINE:
                return new TaskCommand(Deadline.create(cmdDesc));
            case EVENT:
                return new TaskCommand(Event.create(cmdDesc));
            default:
                throw new UnknownCommandException();
            }
        } catch (IllegalArgumentException ex) {
            throw new UnknownCommandException();
        }
    }
}
