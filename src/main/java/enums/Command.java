package enums;

public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    DEADLINE("deadline"),
    TODO("todo"),
    EVENT("event"),
    BY("by"),
    FROM("from"),
    TO("to"),
    SKIP("skip");

    private String cmd;
    private int strLength;

    Command(String cmd) {
        this.cmd = cmd;
        this.strLength = cmd.length();
    }

    public String getCommand() {
        return this.cmd;
    }

    public int getCommandStringLength() {
        return this.strLength;
    }
}
