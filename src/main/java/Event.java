/**
 * The Event class represents a task of type "Event" inherited from the Task class.
 * It contains a description and a time interval during which the event occurs.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event task with the provided description and time interval.
     *
     * @param description The description of the Event task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        String status = getStatusIcon().equals("X") ? "1" : "0";
        return "E" + " | " + status + " | " + super.description + " | " + start + " | " + end;
    }

    /**
     * Returns a string representation of the Event task, including its completion status, description, and time interval.
     *
     * @return A formatted string indicating the task type, completion status, and time interval.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
