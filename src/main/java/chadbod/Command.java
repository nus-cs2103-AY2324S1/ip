package chadbod;

/**
 * The Command Enumeration represents various commands that can be used in the ChadBod application.
 */
public enum Command {
    BYE("bye", "bb"),
    LIST("list", "ls"),
    MARK("mark", "mk"),
    UNMARK("unmark", "umk"),
    TODO("todo", "td"),
    DEADLINE("deadline", "dl"),
    EVENT("event", "ev"),
    DELETE("delete", "del"),
    FIND("find", "f");

    private final String value;
    private final String alias;

    /**
     * Constructs an instance of the Command enumeration.
     *
     * @param value The string representation of the command.
     */
    Command(String value, String alias) {
        this.value = value;
        this.alias = alias;
    }

    /**
     * Returns the string representation of the command.
     *
     * @return The string value of the command.
     */
    public String getValue() {
        return this.value;
    }
    /**
     * Returns the string alias of the command.
     *
     * @return The string alias of the command.
     */
    public String getAlias() {
        return this.alias;
    }
}
