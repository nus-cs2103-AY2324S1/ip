package duke.parser;

import duke.command.*;
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
    public static Command parse(String fullCommand) throws DukeException{
        String[] words = fullCommand.split(" ", 2);
        String firstWord = words[0];

        switch (firstWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int i = Integer.parseInt(words[1]) - 1;
            return new MarkCommand(i);
        case "unmark":
            int j = Integer.parseInt(words[1]) - 1;
            return new UnmarkCommand(j);
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
