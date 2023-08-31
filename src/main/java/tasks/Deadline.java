package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that can be tracked in
 * the chatbot.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Deadline constructor that takes in a String and LocalDateTime.
     * @param desc Description of the deadline.
     * @param by The due date of the deadline.
     */
    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    /**
     * Returns The date and time of the deadline.
     * @return The date and time of the deadline.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns The string representation of Deadline.
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
