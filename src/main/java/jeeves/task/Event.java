package jeeves.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format(this.id + ". [E]" + super.toString() + " (from: " + from + " to: " + to + ")");
    }
}
