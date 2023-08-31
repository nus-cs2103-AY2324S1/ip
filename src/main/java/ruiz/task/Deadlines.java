package ruiz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task that has a deadline.
 */
public class Deadlines extends Task {
    protected LocalDateTime by;

    /**
     * A constructor for the public class Deadlines.
     *
     * @param description contains the description of the deadline.
     * @param by          contains the time which the deadline must be completed by.
     */
    public Deadlines(String description, String by) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String saveTaskString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D" + super.saveTaskString() + " | " + by.format(formatter);
    }

    /**
     * This method converts the value of the deadline into a String type.
     *
     * @return the String representation of the deadline with its type, completion status and completion time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
