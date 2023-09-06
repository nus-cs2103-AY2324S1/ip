package chat.utils;

/**
 * @author juzzztinsoong
 */
public enum Enum {

    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    BYE("bye"),
    FIND("find");

    private String text;

    Enum(String text) {
        this.text = text;
    }

    /**
     * Returns the string associated with each enum to enable the enum mapping.
     * @return the text associated with each enum.
     */
    public String getText() {
        return text;
    }

}
