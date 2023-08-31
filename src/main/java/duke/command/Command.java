package duke.command;

public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    FIND("find");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public static Command getCommand(String value) {
        for (Command cmd : Command.values()) {
            if (cmd.value.equals(value)) {
                return cmd;
            }
        }
        return null;
    }

    public String getCommandName() {
        return this.value;
    }
}