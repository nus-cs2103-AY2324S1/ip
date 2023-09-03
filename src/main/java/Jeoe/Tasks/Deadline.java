package Jeoe.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates the Deadline class.
 * It represents a task that has a deadline.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class Deadline extends Task {

    /** End date & time of the deadline task. */
    protected LocalDateTime by;

    /**
     * Constructor for a Deadline object.
     *
     * @param description The description of the task.
     * @param by The end date & time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns the end date & time of the task.
     *
     * @return String representation of the end date & time of the task.
     */
    @Override
    public String getEndDateTimeString() {
        return this.by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        // return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        // + "T" + this.by.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
