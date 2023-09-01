package deterministicparrot;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    /**
     * The deadline for this task.
     */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given task description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task in string format.
     * @throws DateTimeParseException If the provided deadline is in an invalid date format.
     */
    Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        try {
            this.by = DPUtils.dPTryParseDateTime(by);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for 'by'. Please provide a valid date format.", e);
        }
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string containing the task type, description, and deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DPUtils.dPFormatDateTime(this.by));
    }
}
