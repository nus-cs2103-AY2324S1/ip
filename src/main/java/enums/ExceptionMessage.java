package enums;

/**
 * The `ExceptionMessage` enum represents the possible exception messages used in the Woof application.
 */
public enum ExceptionMessage {
    // INVALID COMMAND EXCEPTION MESSAGES
    INVALID_NUMBER_OF_ARGUMENTS("wrong number of arguments for '%s'!"),
    NULL_ARGUMENT("null argument not allowed for '%s'!"),
    EMPTY_ARGUMENT("I don't want empty arguments for '%s' :("),
    INVALID_COMMAND_WORD("bad command word for '%s'..."),
    INVALID_DATE_TIME_FORMAT("me no understand your datetime format '%s'"),

    // STORAGE EXCEPTION MESSAGES
    UNABLE_TO_READ_FILE("me can't '%s' file. Computer go '%s'"),
    UNABLE_TO_SAVE_FILE("me can't save file. Computer go '%s'"),
    UNABLE_TO_CREATE_FILE("me can't create file. Computer go '%s'"),

    // TASK INDEX EXCEPTION MESSAGE
    INVALID_TASK_INDEX("bad task index '%s', give me integer!"),
    UNABLE_TO_PARSE_INDEX("idk how to parse '%s', Computer go '%s'"),
    TASK_INDEX_NOT_IN_LIST("'%s' not in my task list");

    private final String value;

    /**
     * Constructs a `Exception Message` enum with the given value.
     *
     * @param value The string representation of the command word.
     */
    ExceptionMessage(String value) {
        assert value != null : "value cannot be null";

        this.value = value;
    }

    /**
     * Gets the string representation of the exception message with the appropriate args word.
     *
     * @param args The arguments to replace placeholders in the exception message.
     * @return The string representation of the exception message with the appropriate command word.
     */
    public String toFormattedString(String ... args) {
        return String.format(this.value, args);
    }
}
