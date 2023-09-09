package emiya;

/**
 * An enum that contains all the keywords used to identify what command to run.
 */
public enum Keywords {
    TODO("todo"), DEADLINE("deadline"), EVENT("event"), LIST("list"),
    MARK("mark"), UNMARK("unmark"), DELETE("delete"), FIND("find");

    private String type;
    Keywords(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
