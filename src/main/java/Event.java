public class Event extends Task {
    private String type;
    Event(String description) {
        super(description);
        this.type = "E";
    }

    public String getType() {
        return this.type;
    }
}
