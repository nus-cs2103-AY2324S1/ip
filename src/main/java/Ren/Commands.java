package ren;

/**
 * Represents the commands that the user can input
 */
public enum Commands {
    LS_COMMAND("list"),

    FIND_COMMAND("find"),

    DELETE_COMMAND("delete"),

    EXIT_COMMAND("bye"),

    MARK_COMMAND("mark"),

    UNMARK_COMMAND("unmark");

    private final String value;

    Commands(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
