package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DisplayCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.exceptions.DukeException;

/**
 * The Parser class is responsible for parsing user commands into executable commands.
 */
public class Parser {

    /**
     * Parses the user command and returns the corresponding Command object.
     *
     * @param command The user's input command.
     * @return A Command object that corresponds to the user's command.
     * @throws DukeException If the user command is not recognized or has incorrect format.
     */
    public static Command parse(String command) throws DukeException {
        if (command.equals("list")) {
            return new DisplayCommand(command);
        }
        if (command.startsWith("mark")) {
            return new MarkCommand(command);
        }
        if (command.startsWith("unmark")) {
            return new UnMarkCommand(command);
        }
        if (command.startsWith("todo")) {
            return new AddToDoCommand(command);
        }
        if (command.startsWith("deadline")) {
            return new AddDeadlineCommand(command);
        }
        if (command.startsWith("event")) {
            return new AddEventCommand(command);
        }
        if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        }
        if (command.startsWith("find")) {
            return new FindCommand(command);
        }
        if (command.equals("bye")) {
            return new ByeCommand(command);
        }
        throw new DukeException("Unrecognized command :(");
    }
}
