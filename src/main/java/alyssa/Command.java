package alyssa;

/**
 * This is an enum containing the possible commands given by the user.
 */
public enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, INVALID;
    protected static Command assignCommand(String cmd) {
        switch (cmd) {
        case "bye":
            return Command.BYE;
        case "list":
            return Command.LIST;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "todo":
            return Command.TODO;
        case "event":
            return Command.EVENT;
        case "deadline":
            return Command.DEADLINE;
        case "delete":
            return Command.DELETE;
        case "find":
            return Command.FIND;
        default:
            return Command.INVALID;
        }
    }
}
