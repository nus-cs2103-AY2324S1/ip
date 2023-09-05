package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class for Deadlines tasks.
 */
public class Deadlines extends Task {
    /** Class field by that tells when the deadline is due. */
    protected LocalDate deadlineDate;

    /**
     * Constructor to initialize the Deadlines class.
     *
     * @param description Describes the deadline.
     * @param deadlineDate When the task is due.
     */
    public Deadlines(String description, LocalDate deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * {@inheritDoc}
     */
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("D | %s | %s | %s", doneStatus, this.description, formattedDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, formattedDate);
    }
}
