package Tasks;

public enum Commands {
    BYE,
    LIST,
    MARK,
    UNMARK,
    DELETE,
    TODO,
    DEADLINE,
    EVENT;

    public static boolean contains(String test) {

        for (Commands c : Commands.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }

        return false;
    }
}

