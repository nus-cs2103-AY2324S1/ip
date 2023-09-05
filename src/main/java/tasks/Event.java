package tasks;

import tasks.Task;

/**
 * This class encapsulates an Event child class
 * that contains a description, a start date/time and an end date/time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an Event object.
     *
     * @param description
     * @param start start date/time of the Event.
     * @param end end date/time of the Event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event object that specifies whether it has been completed.
     *
     * @param description
     * @param isDone
     * @param start start date/time of the Event.
     * @param end end date/time of the Event.
     */
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
