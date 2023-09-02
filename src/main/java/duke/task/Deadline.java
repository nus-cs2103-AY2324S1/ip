package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * The Deadline class extends Task and has an additional field
 * to store when the task must be completed by
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Deadline extends Task {

    protected LocalDate by;

    /** Constructor for Deadline */
    public Deadline(String done, String description, LocalDate by) {
        super(description, done);
        this.by = by;
    }

    /**
     * Sets the description and by date.
     *
     * @param description the description of the task.
     * @param by date to finish the task by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String fileRepresentation() {
        return ("D" + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + by + "\n");
    }
}

