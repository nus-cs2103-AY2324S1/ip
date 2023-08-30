package cyrus.commands;

public enum CommandType {
    ADD_TODO,
    ADD_EVENT,
    ADD_DEADLINE,
    BYE,
    DELETE_TASK,
    LIST_TASKS,
    MARK_TASK,
    UNMARK_TASK,
    UNKNOWN;

    public static CommandType fromString(String input) {
        switch (input) {
        case "bye":
            return BYE;
        case "todo":
            return ADD_TODO;
        case "event":
            return ADD_EVENT;
        case "deadline":
            return ADD_DEADLINE;
        case "delete":
            return DELETE_TASK;
        case "list":
            return LIST_TASKS;
        case "mark":
            return MARK_TASK;
        case "unmark":
            return UNMARK_TASK;
        default:
            return UNKNOWN;
        }
    }
}
