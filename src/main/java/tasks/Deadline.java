package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class representing a Deadline task.
 *
 * @author Gallen Ong
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Initialises a Deadline task with a description and deadline.
     *
     * @param description The task description.
     * @param by The task deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Initialises a Deadline Task by retrieving task from existing file.
     *
     * @param status The task status retrieved.
     * @param description The task description retrieved.
     * @param by The task deadline retrieved.
     */
    public Deadline(String status, String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        if (status.equals("1")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return The Deadline task in string format.
     */
    @Override
    public String toString() {
        String strBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + strBy + ")";
    }

    /**
     * Returns a string representation of the Deadline task to be added to a file.
     *
     * @return The Deadline task in string format, specific for file operations.
     */
    @Override
    public String toStringForFile() {
        return "D | " + super.toStringForFile() + " | " + this.by;
    }
}
