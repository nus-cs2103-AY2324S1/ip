package enums;

public enum CommandWord {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    EXIT("exit"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    NULLCOMMAND(""),
    BY("/by"),
    FROM("/from"),
    TO("/to");
    private final String value;

    CommandWord(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CommandWord commandWordToValueMap(String value) {
        for (CommandWord e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return NULLCOMMAND;
    }
}