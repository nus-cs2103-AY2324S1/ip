package command;

/**
 * The CommandKeyword enum represents various keywords related to the commands that Thorndike uses.
 */
public enum CommandKeyword {

    /**
     * Represents the "todo" command keyword.
     */
    TODO,

    /**
     * Represents the "deadline" command keyword.
     */
    DEADLINE,

    /**
     * Represents the "event" command keyword.
     */
    EVENT,

    /**
     * Represents the "exit" command keyword.
     */
    EXIT,

    /**
     * Represents the "delete" command keyword.
     */
    DELETE,

    /**
     * Represents the "find" command keyword.
     */
    FIND,

    /**
     * Represents the "help" command keyword.
     */
    HELP,

    /**
     * Represents the "list" command keyword.
     */
    LIST,

    /**
     * Represents the "mark" command keyword.
     */
    MARK,

    /**
     * Represents the "unmark" command keyword.
     */
    UNMARK,

    /**
     * Represents the "priority" command keyword.
     */
    PRIORITY,

    /**
    * Represents the "toggle" command keyword.
    */
    TOGGLE,

    /**
     * Represents an invalid or unrecognized command keyword.
     */
    INVALID;

    /**
     * Converts a string to the corresponding CommandKeyword enum constant.
     *
     * @param s The string to be converted to a CommandKeyword.
     * @return The CommandKeyword enum constant that corresponds to the input string,
     *         or INVALID if no matching constant is found.
     */
    public static CommandKeyword of(String s) {
        switch (s) {

        case "todo":
            return TODO;

        case "deadline":
            return DEADLINE;

        case "event":
            return EVENT;

        case "bye":
            return EXIT;

        case "delete":
            return DELETE;

        case "find":
            return FIND;

        case "help":
            return HELP;

        case "list":
            return LIST;

        case "mark":
            return MARK;

        case "unmark":
            return UNMARK;

        case "priority":
            return PRIORITY;

        case "toggle":
            return TOGGLE;

        default:
            return INVALID;
        }
    }
}
