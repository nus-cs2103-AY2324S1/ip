package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.StatsCommand;
import duke.task.TaskType;

/**
 * The Parser class is responsible for parsing user input and generating the corresponding commands.
 *
 * @author selwyn
 */
public class Parser {
    /**
     * Parses the user input to generate the appropriate command.
     *
     * @param userInput The input provided by the user.
     * @return A Command object representing the parsed command.
     * @throws DukeException If the input cannot be parsed into a valid command.
     */
    public Command parseCommand(String userInput) throws DukeException {
        String userCommand = getStringAtIndex(userInput, 0);
        String args = getStringAtIndex(userInput, 1);

        switch (userCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand(TaskType.TODO, args);
        case "deadline":
            return new AddCommand(TaskType.DEADLINE, args);
        case "event":
            return new AddCommand(TaskType.EVENT, args);
        case "mark":
            return new MarkCommand(args, true);
        case "unmark":
            return new MarkCommand(args, false);
        case "delete":
            return new DeleteCommand(args);
        case "find":
            return new FindCommand(args);
        case "stats":
            return new StatsCommand();
        default:
            throw new DukeException("I don't understand what you are saying!\n"
                    + "Available commands are list, todo, deadline, event, mark, unmark, delete, stats, bye.");
        }
    }

    /**
     * Extracts a substring from the user input based on the provided position index.
     *
     * @param userInput The input provided by the user.
     * @param position  The position index (0 or 1) of the substring to extract.
     * @return The extracted substring.
     */
    public String getStringAtIndex(String userInput, int position) {
        String[] parsedCommand = userInput.split(" ", 2);
        String strToReturn = null;

        assert(position == 0 || position == 1) : "Position index for getStringAtIndex has to be 0 or 1";

        if (position == 0) {
            strToReturn = parsedCommand[0];
        } else if (position == 1) {
            strToReturn = parsedCommand.length > 1 ? parsedCommand[1] : "";
        }
        return strToReturn;
    }
}
