/**
 * The Event class represents a task that takes place during a specific time frame.
 * It inherits from the Task class and adds start and end time fields.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    private String from;

    /**
     * The end time of the event.
     */
    private String to;

    /**
     * Constructs a new Event object with the specified description, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string indicating the task type, completion status, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
