package enums;

/**
 * The `CommandWord` enum represents the possible command words used in the Woof application.
 */
public enum CommandWord {
    BY("/by"),
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    EVENT("event"),
    EXIT("exit"),
    FIND("find"),
    FROM("/from"),
    HELP("help"),
    LIST("list"),
    MARK("mark"),
    NULL_COMMAND(""),
    SORT("sort"),
    TO("/to"),
    TODO("todo"),
    UNMARK("unmark");

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
            if (e.toValue().equals(value)) {
                return e;
            }
        }
        return NULL_COMMAND;
    }

    /**
     * Gets the string representation of the `CommandWord`.
     *
     * @return The string representation of the `CommandWord`.
     */
    public String toValue() {
        return this.value;
    }
}
