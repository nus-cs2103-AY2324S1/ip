package duke.task;

public class Event extends Task {
    private String start;
    private String end;
    public Event(String taskDescription, String start, String end) {
        super(taskDescription);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
