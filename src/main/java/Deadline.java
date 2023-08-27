
public class Deadline extends Task {
    /**
     * The deadline of the task.
     */
    protected String by;
    /**
     * The type of the task.
     */
    protected String type = "D";
    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }
    /**
     * Returns the deadline of the task.
     *
     * @return The deadline of the task.
     */

    public String getBy() {
        return by;
    }

    @Override
    public String toFileString() {
        return this.type + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (by: " + getBy() + ")";
    }
}
