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
    private int strLength;

    /**
     * Constructor for the enum class
     * @param cmd String of the command
     */
    Command(String cmd) {
        this.cmd = cmd;
        this.strLength = cmd.length();
    }

    /**
     * returns the String representation of the command
     * @return the command String
     */
    public String getCommand() {
        return this.cmd;
    }

    /**
     * returns the string length of the command
     * @return string length of the command
     */
    public int getCommandStringLength() {
        return this.strLength;
    }
}
