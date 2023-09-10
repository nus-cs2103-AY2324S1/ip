package tasks;

/**
 * The Event class represents an event, which is a type of task with a name, start date, and end date.
 * It extends the Task class and provides methods to access the event's start and end dates,
 * as well as a custom implementation of the toString method to represent the event in a specific format.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs a new Event with the specified name, start date, and end date.
     *
     * @param name  The name of the event.
     * @param start The start date of the event.
     * @param end   The end date of the event.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date of the event.
     */
    public String getStart() {
        return this.start;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date of the event.
     */
    public String getEnd() {
        return this.end;
    }

    /**
     * Returns a string representation of the event in the format:
     * [E][ ] name (from: start date to: end date)
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}

