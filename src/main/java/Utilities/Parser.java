package Utilities;

import Commands.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import Utilities.Exceptions.IncompleteDescriptionException;
import Utilities.Exceptions.UnknownCommandException;

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
                t = new ToDo(dataArr[2]);
                break;
            case "D":
                t = new Deadline(dataArr[2], dataArr[3]);
                break;
            case "E" :
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
