package aichan;

/**
 * Represents the type of command.
 */
public enum ActionType {
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    BYE("bye");

    private final String action;
    private ActionType(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return this.action;
    }
}
