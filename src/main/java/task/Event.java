package task;

/**
 * Represents a Task that has a start and end time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a String representation of the Event.
     * @return a String representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a String representation of the Event for the .txt file.
     * @return a String representation
     */
    @Override
    public String toFileString() {
        int bool = this.isDone ? 1 : 0;
        return "E | " + bool + " | " + this.description + " | " + this.start + "-" + this.end + "\n";
    }
}
