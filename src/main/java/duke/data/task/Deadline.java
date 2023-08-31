package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline with a date to complete it by.
 */
public class Deadline extends Task {
    final DateTimeFormatter PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    private LocalDate by;

    /**
     * Returns an instance of {@code Deadline} with the given description, and
     * date to complete it by.
     *
     * @param description The description of the deadline.
     * @param by The date to complete the deadline by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of the due date of the deadline.
     * @param formatter The specified format of the date.
     * @return The String representation of the due date of the deadline.
     */
    public String getFormattedBy(DateTimeFormatter formatter) {
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), getFormattedBy(PRINT_FORMATTER));
    }

    @Override
    public String getStorageString() {
        return "D | " + super.getStorageString() + " | " + getFormattedBy(PARSE_FORMATTER);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;

            return this.description.equals(deadline.description) && this.by.equals(deadline.by);
        }

        return false;
    }
}
