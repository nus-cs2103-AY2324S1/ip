package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a date/time to do by
 */
public class Deadline extends Task {

    /**
     * Deadline that the task is to be done by
     */
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Returns a string representation of the task
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
