package enums;

/**
 * The `CommandWord` enum represents the possible command words used in the Woof application.
 */
public enum CommandWord {
    BYE("bye"),
    FIND("find"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    EXIT("exit"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    NULL_COMMAND(""),
    BY("/by"),
    FROM("/from"),
    TO("/to");

    private final String value;

    /**
     * Constructs a `CommandWord` enum with the given value.
     *
     * @param value The string representation of the command word.
     */
    CommandWord(String value) {
        this.value = value;
    }

    /**
     * Maps a string value to the corresponding `CommandWord` enum.
     * If no match is found, it returns the `NULL_COMMAND` enum.
     *
     * @param value The string value to map to a command word.
     * @return The corresponding `CommandWord` enum, or `NULL_COMMAND` if no match is found.
     */
    public static CommandWord commandWordToValueMap(String value) {
        for (CommandWord e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return NULL_COMMAND;
    }

    /**
     * Gets the string representation of the command word.
     *
     * @return The string representation of the command word.
     */
    public String getValue() {
        return value;
    }
}
