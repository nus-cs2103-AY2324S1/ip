package task;

/**
 * An event class extends the task class. It represents a task with that needs to be completed within
 * a certain time period.
 *
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new task.Event with the specified time period and description.
     *
     * @param description The name of the task.
     * @param from The start date of the task.
     * @param to The end date of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event with the specified time period and description.
     * Event constructed can be marked as completed.
     *
     * @param description The name of the task.
     * @param isDone If task is completed
     * @param from The start date of the task.
     * @param to The end date of the task.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the description of the task with the specified time period.
     *
     * @return A string description of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String fileString() {
        return "E" + super.fileString() + " | " + this.from + "-" + this.to;
    }

}
