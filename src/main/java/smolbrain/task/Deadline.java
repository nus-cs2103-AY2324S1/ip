package smolbrain.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a deadline task with description and end time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a deadline task.
     *
     * @param description Task description.
     * @param by Task end time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    /**
     * Encodes this task into a string for saving into save file.
     *
     * @return Encoded string representation.
     */
    @Override
    public String encode() {
        DateTimeFormatter stringformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "D" + getStatusNumber() + super.description + " /by " + this.by.format(stringformatter);
    }
}
