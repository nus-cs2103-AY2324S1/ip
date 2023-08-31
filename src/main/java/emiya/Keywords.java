package emiya;

public enum Keywords {
    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    private String type;
    Keywords(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
