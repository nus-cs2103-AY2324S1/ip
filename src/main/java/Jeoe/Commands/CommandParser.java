package Jeoe.Commands;

import Jeoe.Exceptions.InvalidCommandException;
import Jeoe.Exceptions.NoCommandException;
import Jeoe.Exceptions.RunException;

/**
 * This class encapsulates Command Parser class which parses commands.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class CommandParser {

    /**
     * Returns the parsed command.
     *
     * @param input the full input by the user.
     * @return Command that is produced from the user input.
     * @throws RunException If the input by the user does not have a valid command.
     */
    public static Command parse(String input) throws RunException {
        String command = input.split(" ")[0]; // even if its empty string, the 0 index will still be ""
        switch (command) {
        case "bye":
            return new ByeCommand(input);
        case "list":
            return new ListCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        case "":
            throw new NoCommandException(command);
        default:
            // throw exception when it doesnt have a command word
            throw new InvalidCommandException(command);
        }
    }
}
