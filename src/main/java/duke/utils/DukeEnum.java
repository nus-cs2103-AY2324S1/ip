package duke.utils;

public enum DukeEnum {

    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    BYE("bye");

    public String text;

    DukeEnum(String text) {
        this.text = text;
    }

    /**
     * Returns the string associated with each enum to enable the enum mapping.
     * 
     * 
     * @return the text associated with each enum.
     */
    public String getText() {
        return this.text;
    }

}
