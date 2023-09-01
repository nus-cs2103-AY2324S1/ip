package duke.commands;

public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    ADD_TODO,
    ADD_DEADLINE,
    ADD_EVENT,
    INVALID;

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
        default:
            return CommandType.INVALID;
        }
    }
}
