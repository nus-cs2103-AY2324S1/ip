package duke.parser;

import duke.CommandType;

/*
 * Encapsulates a parser that parses user input.
 */
public class Parser {

    /*
     * Returns the command type of the given command.
     * 
     * @param command The command to be parsed.
     * @return The command type of the given command.
     */
    public CommandType parseCommandType(String command) {
        if (command.startsWith("list")) {
            return CommandType.LIST;
        } else if (command.startsWith("mark")) {
            return CommandType.MARK;
        } else if (command.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (command.startsWith("todo")) {
            return CommandType.TODO;
        } else if (command.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (command.startsWith("event")) {
            return CommandType.EVENT;
        } else if (command.startsWith("find")) {
            return CommandType.FIND;
        } else if (command.startsWith("bye")) {
            return CommandType.EXIT;
        } else {
            return CommandType.UNKNOWN;
        }
    }

}
