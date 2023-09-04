package duke.parser;

import duke.Duke;

/**
 * Encapsulates a parser that parses user input.
 */
public class Parser {

    /**
     * Returns the command type of the given command.
     *
     * @param command The command to be parsed.
     * @return The command type of the given command.
     */
    public Duke.CommandType parseCommandType(String command) {
        if (command.startsWith("list")) {
            return Duke.CommandType.LIST;
        } else if (command.startsWith("mark")) {
            return Duke.CommandType.MARK;
        } else if (command.startsWith("delete")) {
            return Duke.CommandType.DELETE;
        } else if (command.startsWith("todo")) {
            return Duke.CommandType.TODO;
        } else if (command.startsWith("deadline")) {
            return Duke.CommandType.DEADLINE;
        } else if (command.startsWith("event")) {
            return Duke.CommandType.EVENT;
        } else if (command.startsWith("find")) {
            return Duke.CommandType.FIND;
        } else {
            return Duke.CommandType.UNKNOWN;
        }
    }

}
