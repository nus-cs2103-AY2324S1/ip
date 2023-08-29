package Task;

public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileString() {
        int bool = this.isDone ? 1 : 0;
        return "E | " + bool + " | " + this.description + " | " + this.start + "-" + this.end + "\n";
    }
}
