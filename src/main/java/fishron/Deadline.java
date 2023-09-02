package fishron;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline in the format "dd-MM-yyyy HHmm".
     * @throws FishronException If the provided date/time format is invalid.
     */
    public Deadline(String description, String by) throws FishronException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new FishronException("☹ OOPS!!! Please provide a valid date/time format. e.g. 21-05-2023 1200");
        }
    }

    /**
     * Converts the deadline task to a string representation for storing in a file.
     *
     * @return The string representation of the deadline task for storage.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Converts the deadline task to a user-friendly string representation.
     *
     * @return The user-friendly string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + ")";
    }
}
