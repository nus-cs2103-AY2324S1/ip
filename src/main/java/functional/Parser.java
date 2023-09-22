package functional;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;

/**
 * The Parser class is responsible for parsing user input commands into executable commands
 */
public class Parser {

    /**
     * Parses the user input string and returns the corresponding command object
     *
     * @param fullCommand input to be parsed
     * @return corresponding command type
     */
    public static Command parse(String fullCommand) {
        String[] input = fullCommand.split(" ");
        switch (input[0]) {
        case "bye":
            return new ExitCommand();
        case "delete":
            return new DeleteCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "find":
            return new FindCommand();
        default:
            return new AddCommand();

        }
    }
}
