package duke.commands;

/**
 * The CommandType enum represents different types of commands that can be issued by the user in the Duke application.
 * Each enum value corresponds to a specific command type.
 */
public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    ADD_TODO,
    ADD_DEADLINE,
    ADD_EVENT,
    FIND,
    INVALID;

    /**
     * Returns the CommandType corresponding to the given command string.
     *
     * @param command The command string to be mapped to a CommandType.
     * @return The CommandType that matches the given command string.
     */
    public static CommandType getCommandType(String command) {
        switch (command) {
        case "bye":
            return CommandType.BYE;
        case "list":
            return CommandType.LIST;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "delete":
            return CommandType.DELETE;
        case "todo":
            return CommandType.ADD_TODO;
        case "deadline":
            return CommandType.ADD_DEADLINE;
        case "event":
            return CommandType.ADD_EVENT;
        case "find":
            return CommandType.FIND;
        default:
            return CommandType.INVALID;
        }
    }
}
