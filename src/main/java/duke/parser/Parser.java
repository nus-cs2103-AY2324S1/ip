package duke.parser;

import duke.Duke;

public class Parser {

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
        }
        else {
            return Duke.CommandType.UNKNOWN;
        }
    }

}
