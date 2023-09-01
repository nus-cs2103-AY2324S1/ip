package tasks;

import tasks.Task;

public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toText() {
        return "E " + this.getDoneStatus() + " " + this.description + " /" + this.start + " /" + this.end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (" + this.start + this.end + ")";
    }
}
