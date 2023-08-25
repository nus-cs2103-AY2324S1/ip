package minion.data.task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String datetime;

    /**
     * Creates a deadline object. This is the main constructor of the minion.data.task.Deadline class.
     * @param description Description of deadline.
     * @param isDone Whether the deadline is done.
     * @param datetime Datetime of deadline.
     */
    public Deadline(String description, boolean isDone, String datetime) {
        super(description, isDone);
        this.datetime = datetime;
    }

    /**
     * Creates a deadline object. This calls the main constructor when the default for isDone is false.
     * @param description Description of deadline.
     * @param datetime Datetime of deadline.
     */
    public Deadline(String description, String datetime) {
        this(description, false, datetime);
    }

    /**
     * Returns the string representation of the deadline task.
     * @return string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.datetime+ ")";
    }

    /**
     * Returns a string representation of the deadline to be used in storage.
     * @return string representation of the deadline for storage.
     */
    @Override
    public String toStringStorage() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + this.datetime;
    }
}
