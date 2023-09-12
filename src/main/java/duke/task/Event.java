package duke.task;

/**
 * Represents an event task with from and to date.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.taskType = TaskType.EVENT;
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        this(description, from, to);
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     * Outputs with from and to dates.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " | to: " + this.to + ")";
    }

    /**
     * {@inheritDoc}
     * Outputs with from and to dates.
     */
    @Override
    public String toTxt() {
        return super.toTxt() + this.description + " | " + this.from + " | " + this.to;
    }
}
