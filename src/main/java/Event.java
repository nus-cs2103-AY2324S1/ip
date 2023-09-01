public class Event extends UserInput {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + " | " + this.from + "-" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ")";
    }
}
