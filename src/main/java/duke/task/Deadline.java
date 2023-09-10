package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for the deadline task
     * @param description Description of the task
     * @param by duke.task.Deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        assert (by != null) : "By time of deadline task cannot be null.";
        String byFormatted = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}
