package duke.task;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String fileString() {
        return "E | " + super.fileDescription() + " | " + this.from + " | " + this.to;
    }

    public String toString() {
        return "[E]" + super.taskDescription() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
