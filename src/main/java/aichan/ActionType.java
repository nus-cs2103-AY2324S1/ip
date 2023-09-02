package aichan;

public enum ActionType {
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    BYE("bye"),
    FIND("find");

    private final String action;
    ActionType(String action) {
        this.action = action;
    }

    public String toString(){
        return this.action;
    }
}
