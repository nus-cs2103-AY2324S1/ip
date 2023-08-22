public class Event extends Task{
    protected String from;
    protected String to;
    protected String type = "E";

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
