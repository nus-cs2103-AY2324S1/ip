public enum Commands {
    deadline,
    todo,
    event,
    bye,
    list,
    mark,
    unmark,
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
