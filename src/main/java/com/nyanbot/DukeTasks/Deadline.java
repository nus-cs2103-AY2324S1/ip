package DukeTasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the DukeTasks.Deadline class. Inherits the DukeTasks.Task
 * class and adds on additional unique features.
 *
 * @author Tan Kerway
 */
public class Deadline extends Task {

    // deadline for the deadline task
    private final LocalDateTime by;

    /**
     * Constructor for the DukeTasks.Deadline class.
     *
     * @author Tan Kerway
     * @param description the description of the task
     * @param by the deadline for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = super.parseDate(by, "by");
    }

    /**
     * Constructor for the DukeTasks.Deadline class.
     *
     * @author Tan Kerway
     * @param description the description of the task
     * @param isDone whether the task is done
     * @param by the deadline for the task
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = super.parseDate(by, "by");
    }

    /**
     * Returns the deadline of the DukeTasks.Deadline instance.
     *
     * @author Tan Kerway
     * @return the deadline of the DukeTasks.Deadline instance
     */
    public String getBy() {
        return this.by == null ? null : this.by.format(DateTimeFormatter.ofPattern("MMM d HH:mm yyyy"));
    }

    /**
     * Returns the String representation of a DukeTasks.Deadline class.
     *
     * @author Tan Kerway
     * @return the String representation of a DukeTasks.Deadline class
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: " + super.formatDate(this.by) + ")";
    }
}
