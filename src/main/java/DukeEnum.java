
public enum DukeEnum {

    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    public String text;

    DukeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}


