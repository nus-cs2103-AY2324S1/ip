package chadbod;

/**
 * Enumeration representing various commands that can be used in the ChadBod application.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find");

    private final String value;

    /**
     * Constructor for the Command enumeration.
     *
     * @param value The string representation of the command.
     */
    Command(String value) {
        this.value = value;
    }

    /**
     * Get the string representation of the command.
     *
     * @return The string value of the command.
     */
    public String getValue() {
        return value;
    }
}
