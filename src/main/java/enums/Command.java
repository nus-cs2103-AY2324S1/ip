package enums;

/**
 * Enum class with all the commands listed
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    TODO("todo"),
    EVENT("event"),
    FIND("find"),
    SKIP("skip");

    private String cmd;

    /**
     * Constructor for the enum class
     * @param cmd String of the command
     */
    Command(String cmd) {
        this.cmd = cmd;
    }

    /**
     * returns the String representation of the command
     * @return the command String
     */
    @Override
    public String toString() {
        return cmd;
    }
}
