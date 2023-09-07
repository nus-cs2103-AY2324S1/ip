package duke;

public class Event extends Task {

    protected String from;
    protected String to;
    protected boolean isDone = false;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toSaveString() {
        String divider = " | ";
        return "E" + divider + super.toSaveString() + divider + this.from + divider + this.to;
    }

}