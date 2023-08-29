import java.time.LocalDate;

/**
 * Encapsulates a Deadline task. Deadline tasks have an end time and description.
 */
public class Deadline extends Task {
    private LocalDate endDate;

    /**
     * Constructor for a deadline task.
     *
     * @param endDate     The deadline for deadline task
     * @param description task description
     */
    public Deadline(LocalDate endDate, String description) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Returns the string representation of a deadline task with its status.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endDate + ")";
    }

    /**
     * Returns the string representation of the deadline task to be stored.
     *
     * @return String representing deadline task to be stored.
     */
    @Override
    public String toStored() {
        return "D | " + super.toStored() + " | " + this.endDate;
    }
}
