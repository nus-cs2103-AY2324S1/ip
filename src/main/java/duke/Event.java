package duke;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Event constructor.
     * @param description Event name shown to user.
     * @param from Time the event starts. A string, no type checking done.
     * @param to Time the event ends. A string, no type checking done.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gives string representation of the task, shown to users.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
