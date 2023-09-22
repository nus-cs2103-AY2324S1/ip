package cyrus.commands;

/**
 * Types of commands available to the bot.
 */
public enum CommandType {
    ADD_TODO,
    ADD_EVENT,
    ADD_DEADLINE,
    BYE,
    FIND_TASK,
    DELETE_TASK,
    LIST_TASKS,
    MARK_TASK,
    VIEW_STATISTICS,
    UNMARK_TASK,
    UNKNOWN;

    /**
     * Maps a {@code String} input to a {@code CommandType}.
     *
     * @param input input to map
     * @return mapped {@code CommandType}
     */
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
        case "find":
            return FIND_TASK;
        case "statistics":
            return VIEW_STATISTICS;
        default:
            return UNKNOWN;
        }
    }
}
