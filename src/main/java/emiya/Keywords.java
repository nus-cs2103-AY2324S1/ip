package emiya;

public enum Keywords {
    TODO("todo"), DEADLINE("deadline"), EVENT("event"),
    MARK("mark"), UNMARK("unmark"), DELETE("delete"), FIND("find");

    private String type;
    Keywords(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
