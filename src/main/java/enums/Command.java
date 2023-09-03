package enums;

public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    NULLCOMMAND("");
    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Command commandToValueMap(String value) {
        for (Command e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return NULLCOMMAND;
    }
}