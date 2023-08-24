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
     * @param end         The end time of event
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task
     *
     * @return A formatted string indicated the task type, completion status and time interval
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to:" + end + ")";
    }
}
