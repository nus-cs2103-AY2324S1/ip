public enum Command {
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    BYE("bye"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    ON("on");


    private final String command;

    private Command(final String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
