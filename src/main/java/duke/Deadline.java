package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


/**
 * Encapsulates deadline which are Tasks with start and end.
 */
public class Deadline extends Task {
    private LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)) + ")";
    }

    /**
     * Returns a String representation of the task for storage.
     */
    @Override
    public String toTxt() {
        return "D | " + super.toTxt()
                + " | " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
    }
}
