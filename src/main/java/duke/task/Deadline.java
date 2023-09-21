package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a Deadline.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description String describing the Deadline.
     * @param by Deadline date in MMM DD YYYY format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline.
     *
     * @return string representation of the Deadline task.
     */
    @Override
    public String toString() {
        String msg = "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + ")";
        return msg;
    }
}
