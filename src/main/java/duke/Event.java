package duke;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to, char taskType) {
        super(description, taskType);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + "-" + to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
