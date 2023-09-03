package oscar.essential;

import oscar.command.Command;
import oscar.command.DeadlineCommand;
import oscar.command.DeleteCommand;
import oscar.command.EventCommand;
import oscar.command.ExitCommand;
import oscar.command.FindCommand;
import oscar.command.ListCommand;
import oscar.command.MarkCommand;
import oscar.command.TodoCommand;
import oscar.command.UnmarkCommand;
import oscar.exception.OscarException;

/**
 * Handles raw user input and invokes the corresponding command.
 */
public class Parser {
    /**
     * Handles raw user input and invokes the corresponding command.
     *
     * @param fullCommand Raw user input.
     * @return Corresponding command.
     * @throws OscarException Invalid command.
     */
    public static Command parse(String fullCommand) throws OscarException {
        String[] splits = fullCommand.split(" ", 2);
        String command = splits[0].toLowerCase();
        String details = fullCommand.length() > command.length() ? splits[1] : "";
        switch (command) {
        // Exit programme if user enters "bye" command
        case "bye":
            return new ExitCommand();

        // Display text stored by user in chronological order if
        // user enters "list" command
        case "list":
            return new ListCommand();

        // Mark task as done if user enters "mark" command
        case "mark":
            return new MarkCommand(details);

        // Mark task as not done if user enters "unmark" command
        case "unmark":
            return new UnmarkCommand(details);

        // Delete a task if user enters "delete" command
        case "delete":
            return new DeleteCommand(details);

        // Create a new todo task if user enters "todo" command
        case "todo":
            return new TodoCommand(details);

        // Create a new deadline task if user enters "deadline" command
        case "deadline":
            return new DeadlineCommand(details);

        // Create a new event task if user enters "event" command
        case "event":
            return new EventCommand(details);

        // Find
        case "find":
            return new FindCommand(details);

        default:
            // Default response for unknown commands
            throw new OscarException("Sorry! Oscar does not recognise this command\n");
        }
    }
}
