package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * A Deadline is a task with an additional LocalDateTime attribute to indicate its deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified description and deadline date-time.
     *
     * @param description The description of the deadline.
     * @param by The deadline date-time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, false);
        this.by = by;
    }

    /**
     * Constructs a Deadline object with the specified description, completion status, and deadline date-time.
     *
     * @param description The description of the deadline.
     * @param isDone Indicates if the task has been completed.
     * @param by The deadline date-time of the task.
     */
    public Deadline(String description, Boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the deadline date-time as a formatted string.
     *
     * @return A string representing the formatted deadline date-time.
     */
    public String getByString() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Converts the Deadline object to a string format suitable for saving to a file.
     *
     * @return A string representation of the Deadline object for saving purposes.
     */
    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + getByString();
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByString() + ")";
    }
}
