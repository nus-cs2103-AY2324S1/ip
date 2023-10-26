package jarvis.commands;

/**
 * Enumeration class containing all command types.
 *
 * @author Shishir
 */
public enum CommandType {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    REMIND("remind");
    private final String commandString;

    /**
     * Constructs the enumeration object.
     * @param commandString Input command.
     */
    CommandType(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Returns string representation of the enumeration.
     * @return String representation of the enumeration.
     */
    @Override
    public String toString() {
        return commandString;
    }
}
