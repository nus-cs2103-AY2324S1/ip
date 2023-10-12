package aj;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for Deadline Task.
 */
public class Deadline extends Task {

    private final LocalDate by;

    Deadline(String taskName, boolean isMarked, LocalDate by) {
        super(taskName, isMarked);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }


}
