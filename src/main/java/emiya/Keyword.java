package emiya;

/**
 * An enum that contains all the keywords used to identify what command to run.
 */
public enum Keyword {
    TODO("todo"), DEADLINE("deadline"), EVENT("event"), LIST("list"),
    MARK("mark"), UNMARK("unmark"), DELETE("delete"), FIND("find"),
    UNKNOWN("unknown");

    private final String type;
    Keyword(String type) {
        this.type = type;
    }


    /**
     * Returns the respective command of the input string.
     *
     * @param value The input String containing the command.
     * @return The respective command of the input string.
     */
    public static Keyword getCommand(String value) {
        for (Keyword kwd : Keyword.values()) {
            if (kwd.type.equals(value)) {
                return kwd;
            }
        }
        return UNKNOWN;
    }

}
