package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline task.
 */
public class Deadline extends Task {
    /** The datetime of the deadline of the task **/
    protected LocalDateTime by;

    /**
     * Constructor for Deadline instance.
     * @param description String description of deadline
     * @param by datetime of the deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    /**
     * Returns string representation of task to be written into save file.
     * @return string representation of task
     */
    public String toWriteString() {
        return "D | " + super.toWriteString() + " | " + this.by;
    }
}