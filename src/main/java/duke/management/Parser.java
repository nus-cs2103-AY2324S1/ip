package duke.management;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.NoteCommand;
import duke.command.NotesCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

/**
 * Provides parse method to execute different commands.
 */
public class Parser {
    /**
     * Parses user input into the various commands.
     *
     * @return Bot's response in String.
     */
    public static Command parse(String command) {
        String[] commandArr = command.split(" ", 2);
        switch (commandArr[0]) {
        case "note":
            return new NoteCommand(command);
        case "notes":
            return new NotesCommand(command);
        case "find":
            return new FindCommand(command);
        case "list":
            return new ListCommand(command);
        case "mark":
            return new MarkCommand(command);
        case "unmark":
            return new UnmarkCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "event":
            return new EventCommand(command);
        case "todo":
            return new TodoCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "bye":
            return new ByeCommand(command);
        default:
            throw new DukeException(DukeException.UNKNOWN);
        }
    }
}
