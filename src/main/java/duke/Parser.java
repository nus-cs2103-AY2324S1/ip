package duke;

import duke.commands.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.Exceptions.IncompleteDescriptionException;
import duke.Exceptions.UnknownCommandException;

public class Parser {

    private enum CommandHeaders {
        BYE,
        MARK,
        UNMARK,
        LIST,
        DELETE,
        TODO,
        DEADLINE,
        EVENT;
    }

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
        if (dataArr[1].equals("1")) t.markDone();
        return t;
    }

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
