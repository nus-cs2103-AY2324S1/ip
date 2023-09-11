package duke;

/**
 * The duke.Event class represents a task with a start date and end date.
 * It extends the duke.Task class.
 * Adds a 'from' and 'to' fields to store the state date and end date respectively of the event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for duke.Event class.
     *
     * @param description the description of the task
     * @param from        the start date of the task
     * @param to          the end date of the task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides toString method from duke.Task
     * returns a String representation of duke.Event task.
     *
     * @return The String representation of duke.Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
