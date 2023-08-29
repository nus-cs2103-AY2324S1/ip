package duke.parser;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

/**
 * Parses user input to create corresponding Command objects for Duke.
 * Determines the type of command based on the keyword in the input.
 */
public class Parser {

    /**
     * Parses the full user input and creates the appropriate Command object.
     *
     * @param fullCommand The full user input.
     * @return The Command object corresponding to the user input.
     * @throws DukeException If the user input is not recognized.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ", 2);
        String keyword = parts[0];
        String details = parts.length == 2 ? parts[1] : "";

        switch (keyword) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(details);
        case "unmark":
            return new UnmarkCommand(details);
        case "todo":
            return new TodoCommand(details);
        case "deadline":
            return new DeadlineCommand(details);
        case "event":
            return new EventCommand(details);
        case "delete":
            return new DeleteCommand(details);
        case "find":
            return new FindCommand(details);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
