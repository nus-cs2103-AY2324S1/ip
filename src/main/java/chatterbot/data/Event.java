package chatterbot.data;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        if (description.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! Invalid input! No ttask description.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }

    @Override
    public String forFile() {
        return "event " + this.description + "/from " + this.from + "/to " + this.to;
    }
}
