package smolbrain.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates an event task with description, start and end time.
 */
public class Event extends Task {

    /** Start date and time of deadline*/
    protected LocalDateTime from;
    /** End date and time of deadline*/
    protected LocalDateTime to;

    /**
     * Creates a deadline task.
     *
     * @param description Task description.
     * @param from Task start time.
     * @param to Task end time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter) + ")";
    }

    /**
     * Encodes this task into a string for saving into save file.
     *
     * @return Encoded string representation.
     */
    @Override
    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return "E" + getStatusNumber() + super.description + " /from " + this.from.format(formatter)
                + " /to " + this.to.format(formatter);
    }
}
