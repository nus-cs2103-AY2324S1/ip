package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UndoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;

/**
 * Parses user input and generates corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the given full command and returns the corresponding Command object.
     *
     * @param fullCommand The full user input command.
     * @return The parsed Command object corresponding to the input.
     * @throws DukeException If there is an issue parsing the command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        assert fullCommand != null && fullCommand != "" : "User command cannot be empty!";

        String[] words = fullCommand.split(" ", 2);
        String type = words[0];

        switch (type) {
        case "bye":
            return new ExitCommand();
        case "undo":
            return new UndoCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int i = Integer.parseInt(words[1]) - 1;
            return new MarkCommand(i);
        case "unmark":
            int j = Integer.parseInt(words[1]) - 1;
            return new UnmarkCommand(j);
        case "find":
            return new FindCommand(words[1].trim());
        case "delete":
            int k = Integer.parseInt(words[1]) - 1;
            return new DeleteCommand(k);
        case "todo":
            return new AddCommand(words, "T");
        case "deadline":
            return new AddCommand(words, "D");
        case "event":
            return new AddCommand(words, "E");
        default:
            return new UnknownCommand();
        }
    }
}
