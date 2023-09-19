package duke;

/**
 * Categorises task as Event.
 */
public class Event extends Task {

    /** String to hold start time of an event */
    protected String from;

    /** String to hold end time of an event */
    protected String to;

    /**
     * To create a new task categorised as event
     *
     * @param description Description of the task
     * @param from Start time of an event task
     * @param to End time of an event task
     */
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
