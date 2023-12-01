package duchess.command;

/**
 * An enum for indicating Task statuses.
 */
public enum CommandType {
    LIST,
    MARK,
    UNMARK,
    DELETE,
    SEARCH,
    TODO,
    DEADLINE,
    EVENT,
    ADD_TAG,
    DELETE_TAG,
    UNRECOGNIZED
}
