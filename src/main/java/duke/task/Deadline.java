package duke.task;

import duke.exceptions.DukeInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class which represents
 * a task that needs to be done before a specific date.
 *
 * @author Andrew Daniel Janong
 */
public class Deadline extends Task {
    /** Due date of the deadline */
    protected LocalDate endTime;

    /**
     * Creates a Deadline object.
     *
     * @param description Description of the task.
     * @param endTime Due date of the deadline.
     */
    public Deadline(String description, String endTime) throws DukeInvalidDateException{
        super(description);
        try {
            this.endTime = LocalDate.parse(endTime);
        } catch (DateTimeParseException error) {
            throw new DukeInvalidDateException("Date must be of the form yyyy-mm-dd.");
        }

    }

    @Override
    public String getDataRepresentation() {
        return "D|" + super.getDataRepresentation() + "|" + endTime;
    }

    /**
     * Returns s string representation of a Deadline.
     * Uses an extra [D] to represent a Deadline and the due date.
     *
     * @return the string representation of the Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
