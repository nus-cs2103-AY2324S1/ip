package duke;

/**
 * This represents the different commands that can be used in the Duke program.
 */
public enum Commands {
    //alr implemented enums.
    deadline,
    todo,
    event,
    bye,
    list,
    mark,
    unmark,
    delete,
    unknown;
    /**
     * Takes a string and returns the corresponding Commands enum value.
     * If the string does not match any of the enum values, it returns the unknown value.
     *
     * @param cmd The string to be converted to a Commands enum value.
     * @return The corresponding Commands enum value or unknown if no match is found.
     */
    public static Commands get(String cmd) {
        Commands c;
        try {
            c = Commands.valueOf(cmd);
        } catch (IllegalArgumentException | NullPointerException e) {
            c = Commands.unknown;
        }
        return c;
    }
}
