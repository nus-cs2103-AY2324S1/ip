package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ReminderCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;


/**
 * Class responsible for parsing user input and returning
 * a command object for execution.
 */
public class Parser {
    /**
     * Parses the user input into a duke.command.Command object.
     *
     * @param userInput The input string from the user.
     * @return The corresponding duke.command.Command object.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] words = userInput.trim().toLowerCase().split("\\s+");
        switch (words[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "todo":
        case "event":
        case "deadline":
            return new AddCommand();
        case "delete":
            return new DeleteCommand();
        case "find":
            return new FindCommand();
        case "reminders":
            return new ReminderCommand();
        default:
            throw new DukeException(userInput + "?\nI don't know what that is!");
        }
    }
}
