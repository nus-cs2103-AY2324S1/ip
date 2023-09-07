package duke;

import java.time.LocalDateTime;

/**
 * This class encapsulates a Deadline task that has the date/time to indicate when the task is due.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description the description of the deadline task.
     * @param by the date/time of when the deadline task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDateToString(this.by) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        return "D | " + super.convertToSaveFormat() + " | "
                + convertDateToString(this.by);
    }
}
