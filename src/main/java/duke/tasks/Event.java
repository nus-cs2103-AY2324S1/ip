package duke.tasks;

/**
 * Represents a task that occurs during a specific period.
 *
 * <p> An Event task is an extension of the basic Task, with additional characteristics of
 * "from" and "to" fields representing the start and end times of the event, respectively. </p>
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates a new event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(TaskType.EVENT, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string showing the event task's type, status, description, start time, and end time.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a transformed format of the event task, useful for data storage or other operations.
     *
     * @return A string that represents the event task in a unique format.
     */
    @Override
    public String transformFormat() {
        return super.transformFormat() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns the start of this task.
     *
     * @return A {@code String} representing the start of the task.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end of this task.
     *
     * @return A {@code String} representing the end of the task.
     */
    public String getTo() {
        return this.to;
    }
    /**
     * Modifies the from of this task.
     */
    public void setFrom(String newFrom) {
        this.from = newFrom;
    }
    /**
     * Modifies the to of this task.
     */
    public void setTo(String newTo) {
        this.to = newTo;
    }
}
