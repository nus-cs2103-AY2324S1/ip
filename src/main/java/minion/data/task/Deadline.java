package minion.data.task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private String datetime;

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
     * Returns whether the deadline contains the query.
     * @param query the query.
     * @return whether the deadline contains the query.
     */
    @Override
    public boolean contains(String query) {
        return description.contains(query) || datetime.contains(query);
    }

    /**
     * Returns the string representation of the deadline task.
     * @return string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datetime + ")";
    }

    /**
     * Returns a string representation of the deadline to be used in storage.
     * @return string representation of the deadline for storage.
     */
    @Override
    public String toStringStorage() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + datetime;
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Deadline)) {
            return false;
        }
        Deadline t = (Deadline) o;
        return description.equals(t.description) && isDone == t.isDone && datetime.equals(t.datetime);
    }
}
