package functional;

import commands.*;

/**
 *The Parser class is responsible for parsing user input commands into executable commands
 */
public class Parser {

    /**
     * Parses the user input string and returns the corresponding command object
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
        default:
            return new AddCommand();

        }
    }
}
