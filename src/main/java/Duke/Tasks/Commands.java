package Duke.Tasks;

/**
 * Types of commands that DukeBot accepts.
 */
public enum Commands {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT,

    FIND;

    /**
     * Tests to see if a string contains this enum.
     *
     * @param test The test string
     * @return true if is an enum
     */
    public static boolean contains(String test) {

        for (Commands c : Commands.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}

