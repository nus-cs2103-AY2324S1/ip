package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;


/**
 * Handles the parsing of user input and executes corresponding actions.
 */
public class Parser {

    public Command parseCommand(String userInput) throws DukeException {
        String userCommand;
        String description;
        String[] parsedCommand = userInput.split(" ", 2);
        userCommand = parsedCommand[0];
        description = parsedCommand.length > 1 ? parsedCommand[1] : "";

        switch (userCommand) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new AddCommand("Todo", description);
            case "deadline":
                return new AddCommand("Deadline", description);
            case "event":
                return new AddCommand("Event", description);
            case "mark":
                return new MarkCommand(description, true);
            case "unmark":
                return new MarkCommand(description, false);
            case "delete":
                return new DeleteCommand(description);
            case "find":
                return new FindCommand(description);
            default:
                throw new DukeException("I don't understand what you are saying!");
        }
    }
}
