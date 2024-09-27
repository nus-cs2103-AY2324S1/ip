package oscar.essential;

import oscar.command.Command;
import oscar.command.DeadlineCommand;
import oscar.command.DeleteCommand;
import oscar.command.EventCommand;
import oscar.command.ExitCommand;
import oscar.command.FindCommand;
import oscar.command.ListCommand;
import oscar.command.MarkCommand;
import oscar.command.NoteCommand;
import oscar.command.TodoCommand;
import oscar.command.UnmarkCommand;
import oscar.exception.OscarException;

/**
 * Handles raw user input and invokes the corresponding command.
 */
public class Parser {
    /**
     * Handles raw user input and invokes callCommand to handle the command.
     *
     * @param fullCommand Raw user input.
     * @return Invoked command.
     * @throws OscarException Invalid command.
     */
    public static Command parse(String fullCommand) throws OscarException {
        String[] splits = fullCommand.split(" ", 2);
        String command = splits[0].toLowerCase();
        String details = fullCommand.length() > command.length() ? splits[1] : "";
        return callCommand(command, details);
    }

    /**
     * Invokes the corresponding command with details.
     *
     * @param command User input command.
     * @param details Details of command.
     * @return Corresponding command.
     * @throws OscarException Invalid command.
     */
    private static Command callCommand(String command, String details) throws OscarException {
        switch (command) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(details);

        case "unmark":
            return new UnmarkCommand(details);

        case "delete":
            return new DeleteCommand(details);

        case "todo":
            return new TodoCommand(details);

        case "deadline":
            return new DeadlineCommand(details);

        case "event":
            return new EventCommand(details);

        case "note":
            return new NoteCommand(details);

        case "find":
            return new FindCommand(details);

        default:
            throw new OscarException("Sorry! Oscar does not recognise this command\n");
        }
    }
}
