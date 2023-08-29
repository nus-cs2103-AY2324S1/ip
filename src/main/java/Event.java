/**
 * The Event class represents a task with a start date and end date.
 * It extends the Task class.
 * Adds a 'from' and 'to' fields to store the state date and end date respectively of the event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event class.
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
     * Overides toString method from Task
     * returns a String representation of Event task.
     *
     * @return The String representation of Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
