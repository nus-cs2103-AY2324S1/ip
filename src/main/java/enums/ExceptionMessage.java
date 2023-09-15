package enums;

/**
 * The `ExceptionMessage` enum represents the possible exception messages used in the Woof application.
 */
public enum ExceptionMessage {
    INVALID_NUMBER_OF_ARGUMENTS("Invalid number of arguments for the '%s' command."),
    NULL_ARGUMENT("null argument not allowed for '%s'"),
    EMPTY_ARGUMENT("Empty argument not allowed for '%s'"),
    INVALID_COMMAND_WORD("Invalid command word for '%s' command."),
    INVALID_SUB_COMMAND_WORD("Invalid subcommand word for '%s' command."),
    INVALID_DATE_TIME_FORMAT("Invalid datetime format");

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
     * Gets the string representation of the exception message with the appropriate command word.
     *
     * @param args The arguments to replace placeholders in the exception message.
     * @return The string representation of the exception message with the appropriate command word.
     */
    public String getValueFormat(String ... args) {
        return String.format(this.value, (Object) args);
    }
}
