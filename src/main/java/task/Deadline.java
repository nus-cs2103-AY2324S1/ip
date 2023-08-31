package task;

import exceptions.DukeInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that needs to be done before a specific date/time.
 *
 * @author Andrew Daniel Janong
 */
public class Deadline extends Task {
    /**
     * The end time or due date of the deadline.
     */
    protected LocalDate endTime;

    /**
     * A public constructor for the task.Deadline.
     * @param description
     * @param endTime
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
    public String toDataRepresentation() {
        return "D|" + super.toDataRepresentation() + "|" + endTime;
    }

    /**
     * A string representation of a task.Deadline.
     * Uses an extra [D] to represent a task.Deadline and the due date.
     * @return the string representation of the task.Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
