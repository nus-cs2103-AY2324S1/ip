import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task that has a description and a due date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor with description and due date/time.
     *
     * @param description The description of the deadline.
     * @param by The due date/time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    // Convert string by into LocalDateTime by
    private LocalDateTime parseDateTime(String dateTimeString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("☹ OOPS!!! Invalid date/time format. Please use dd/MM/yyyy HHmm");
        }
    }

    // Format LocalDateTime by as a string
    private String formatDateTime(LocalDateTime dateTime) {
        //  "a" represents the AM/PM marker
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(by) + ")";
    }
}
