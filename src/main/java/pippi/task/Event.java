package pippi.task;

/**
 * The Event class represents a specific type of task
 * that aside from description also includes specified
 * start and end time
 *
 * @author Nathan
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructs an Event instance with a description, start and end period.
     *
     * @param desc The description of the Event
     * @param start the start of the Event
     * @param end the end of the Event
     */
    public Event(String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the String representation of an event task to the UI
     * @return Event string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + "to: " + this.end + ")";
    }

    /**
     * Returns the String representation of an event task written to the memory
     * @return Event string representation
     */
    @Override
    public String toMemory() {
        return "E " + super.getStatus() + super.getDescription() + " | " + start + "to " + end;
    }
}
