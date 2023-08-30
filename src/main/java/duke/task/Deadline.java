package duke.task;

import java.time.LocalDateTime;

/**
 * The Deadline class represents a task with a specific end date and time.
 *
 * @author selwyn
 */
public class Deadline extends Task {
    /** The end date and time of the deadline. */
    private LocalDateTime endDateTime;

    /**
     * Constructs a Deadline object with the specified description and end date and time.
     *
     * @param detail The description of the deadline.
     * @param endDateTime The end date and time of the deadline.
     */
    public Deadline(String detail, LocalDateTime endDateTime) {
        super(detail);
        this.endDateTime = endDateTime;
    }

    /**
     * Returns a string representation of the Deadline object, including its completion status, description,
     * and end date and time.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + super.getDisplayDateTime(this.endDateTime) + ")";
    }
}
