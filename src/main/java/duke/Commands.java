package duke;

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
