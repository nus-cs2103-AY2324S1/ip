package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a date/time to do by
 */
public class Deadline extends Task {

    public static final String TASK_TYPE = "D";

    /** Deadline that the task is to be done by */
    protected LocalDateTime by;

    /**
     * Creates a deadline with the given description, and the date/time the task is to be done by
     *
     * @param description description of the task
     * @param by date/time the task is to be done by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.taskType = TASK_TYPE;
    }

    /**
     * Formats the task into a string to be written to the storage file
     *
     * @return string to be written to the storage file
     */
    public String formatForFileWriting() {
        return this.getDescription() + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
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
