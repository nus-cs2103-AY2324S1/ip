package duke;

/**
 * Categorises task as Event.
 */
public class Event extends Task {
    
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns string representation of the event task in the
     * format that will be outputted to the user.
     *
     * @return String representation of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
