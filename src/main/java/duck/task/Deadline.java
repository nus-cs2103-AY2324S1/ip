package duck.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Consists of information related to Deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline Object.
     * @param description Contains the type of deadline
     * @param by Contains the date and time so as to when should the deadline be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * String representation of the deadline.
     * @return formatted string with the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString().substring(3) + " (by: " + this.getBy() + ")";
    }

    /**
     * Returns the type of identifier.
     * @return type identifier "D" representing Deadline task.
     */
    public String type() {
        return "D";
    }
    /**
     * Returns the formatted deadline date and time as a string.
     * @return Date and Time in a particular format.
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}

