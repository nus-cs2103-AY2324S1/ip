package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.InvalidTaskException;

/**
 * Represents a parser class to parse user commands for Duke
 */
public class Parser {

    /**
     * Parses a string input and returns the corresponding Command object.
     * If the command is invalid throws InvalidTaskException
     * @param fullCommand The String command that is inputeby the user.
     * @return Command object corresponding to the string command.
     */
    public static Command parse(String fullCommand) throws InvalidTaskException {
        //read first word in command
        String[] words = fullCommand.split(" ", 2);
        switch (words[0]) {
        case "bye":
            return new ExitCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "list":
            return new ListCommand(fullCommand);
        case "mark":
            return new MarkCommand(fullCommand);
        case "unmark":
            return new UnmarkCommand(fullCommand);
        case "todo":
            return new TodoCommand(fullCommand);
        case "deadline":
            return new DeadlineCommand(fullCommand);
        case "event":
            return new EventCommand(fullCommand);
        case "find":
            return new FindCommand(fullCommand);
        default:
            throw new InvalidTaskException();
        }
    }
}
