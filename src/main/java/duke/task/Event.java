package duke.task;

/**
 * Represents a duke.task.Task with a start and end date.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for a duke.task.Event instance.
     *
     * @param description The description of the underlying task.
     * @param from        The date on which the event begins.
     * @param to          The date on which the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Provides the string representation of the duke.task.Event instance.
     *
     * @return A string with the relevant information of the duke.task.Event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    /**
     * Get a formatted string of the duke.task.Event task to add to the save file.
     *
     * @return A formatted string with the relevant information for the save file.
     */
    @Override
    public String getSaveString() {
        return String.format("E | %d | %s | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                this.from,
                this.to);
    }
}
