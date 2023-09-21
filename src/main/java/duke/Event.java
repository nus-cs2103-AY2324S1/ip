package duke;

/**
 * Represents an event task that occurs during a specified time range.
 *
 * @author Qin Yan Er
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates a new Event instance.
     *
     * @param description The description of the event.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string to save the event task to a file.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String saveTask() {
        return "E" + " | " + super.saveTask() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns a formatted string for display.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
