package bob.task;

/**
 * A Deadline is a task that has a due date. Hence, it stores a date at which the
 * task should be completed by.
 */
public class Deadline extends Task {

    protected String dueDate;

    /**
     * Constructor of the Deadline Class.
     *
     * @param description Text description of Deadline Task
     * @param dueDate Due date of Deadline task
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = DateFormatter.format(dueDate, "MMM d yyyy");
    }

    /**
     * Constructor of the Deadline Class.
     * Instantiates an instance of a deadline and set its completion status
     * according to boolean value provided.
     *
     * @param description Text description of Deadline Task
     * @param by Due date of Deadline task
     * @param isDone Completion status of Deadline Task
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.dueDate = DateFormatter.format(by, "MMM d yyyy");
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("D|%s|%s", super.convertToFileFormat(), this.dueDate);
    }
}
