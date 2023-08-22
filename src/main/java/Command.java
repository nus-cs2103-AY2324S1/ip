public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline");

    private final String command;

    public String getCommand() {
        return this.command;
    }

    private Command(String command) {
        this.command = command;
    }
}

