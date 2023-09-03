package Jeoe.Tasks;

/**
 * This class encapsulates the Event class.
 * It represents a task that has a start date & time and a deadline.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for an Event object.
     *
     * @param description The description of the task.
     * @param from The start date & time of the task.
     * @param to The end date & time of the task.
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date & time of the task.
     *
     * @return String representation of the start date & time of the task.
     */
    @Override
    public String getStartDateTimeString() {
        return this.from;
    }

    /**
     * Returns the end date & time of the task.
     *
     * @return String representation of the end date & time of the task.
     */
    @Override
    public String getEndDateTimeString() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
