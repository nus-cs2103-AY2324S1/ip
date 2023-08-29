package Bob.task;

/**
 * A Deadline is a task that has a due date. Hence, it stores a date at which the
 * task should be completed by.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor of the Deadline Class.
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateFormatter.format(by, "MMM d yyyy");
    }

    /**
     * Constructor of the Deadline Class.
     * Instantiates an instance of a deadline and set its completion status
     * according to boolean value provided.
     *
     * @param description
     * @param by
     * @param isDone
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = DateFormatter.format(by, "MMM d yyyy");
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("D|%s|%s", super.convertToFileFormat(), this.by);
    }
}
