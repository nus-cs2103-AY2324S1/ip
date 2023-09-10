package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    /**
     * The date of the deadline.
     */
    private final LocalDateTime dateTime;

    /**
     * Creates a deadline with the given description and date.
     * @param description The description of the deadline.
     * @param dateTime The date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        assert description != null && !description.trim().isEmpty() : "description should not be null";
        super.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of the deadline.
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        String newDateTime = dateTime.format(super.formatter);
        return "[D]" + super.toString() + " (by: " + newDateTime + ")";
    }
}
