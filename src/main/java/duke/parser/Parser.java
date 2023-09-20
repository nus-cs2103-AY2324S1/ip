package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Parser class to parse user input.
 */
public class Parser {
    /**
     * Parses user input strings into meaningful Command objects.
     *
     * @param userInput The full user input, in a line.
     * @return A Command object.
     * @throws DukeException If user input is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        try {
            String[] tokens = userInput.split(" ", 2);
            String keyword = tokens[0].strip();
            String details = tokens.length > 1 ? tokens[1].strip() : null;

            switch (keyword.toLowerCase()) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(details);
            case "unmark":
                return new UnmarkCommand(details);
            case "delete":
                return new DeleteCommand(details);
            case "todo":
                return new ToDoCommand(details);
            case "deadline":
                return new DeadlineCommand(details);
            case "event":
                return new EventCommand(details);
            case "find":
                return new FindCommand(details);
            default:
                throw new DukeException(" ☹ I'm not ChatGPT, cannot understand what you mean.");
            }
        } catch (NullPointerException e) {
            // If userInput is null, .split of `userInput` will fail.
            throw new DukeException(" ☹ What are you exactly asking me to do?");
        }
    }
}
