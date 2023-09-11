package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type Deadline.
 */
public class Deadline extends Task {

    private final LocalDateTime by;
    private final DateTimeFormatter formatter;

    /**
     * A constructor for a task of type Deadline.
     *
     * @param description the task details.
     * @param by the deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the task in file format.
     *
     * @return the string representation in file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + this.isDone + " | " + this.description + " | " + this.by.format(formatter);
    }

    /**
     * Returns the task type.
     *
     * @return the task type.
     */
    @Override
    public String getTaskType() {
        return "deadline";
    }
}

