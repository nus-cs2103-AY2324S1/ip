package seedu.duke;

/**
 * Encapsulates the Event class.
 * An event is a Task with a start and end date.
 */
public class Event extends Task {
    protected String type = "E";
    protected String start;
    protected String end;
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "[" + this.type + "]";
    }

    @Override
    public String getTimeInfo() {
        return "(from: " + this.start + " to: " + this.end + ")";
    }
}
