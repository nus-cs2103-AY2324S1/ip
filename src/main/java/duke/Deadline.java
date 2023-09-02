package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime dueDate;

    /**
     * Constructor to initialise a Deadline object.
     *
     * @param desc Description of the deadline task.
     * @param dueDate Due date and time of deadline task.
     */
    public Deadline(String desc, LocalDateTime dueDate) {
        super(desc);
        this.dueDate = dueDate;
    }

    /**
     * Converts the given LocalDateTime into a string of the MMM d yyyy HH:mm format.
     *
     * @param dt The LocalDateTime to be formatted.
     * @return The formatted string representation of the LocalDateTime.
     */
    public static String formatDateTime(LocalDateTime dt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

        return dt.format(dateTimeFormatter);
    }

    /**
     * Converts the Deadline object to the specific format to be stored in the text file.
     *
     * @return The specific string representation of the Deadline object to be stored.
     */
    @Override
    public String toSavedString() {
        return String.format("[D] %s//%s//", super.toSavedString(), formatDateTime(this.dueDate));
    }

    /**
     * Converts the Deadline object to its string representation.
     *
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), formatDateTime(this.dueDate));
    }
}
