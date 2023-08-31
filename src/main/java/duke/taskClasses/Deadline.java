package duke.taskClasses;

import duke.exception.InvalidDateTimeException;
import duke.utils.DateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 * A deadline task has a description and a specific end date and time by which the task should be completed.
 */
public class Deadline extends Task {

    /** The end date and time for the deadline task. */
    protected LocalDateTime date;

    /**
     * Constructs a new Deadline object with a given description and date.
     *
     * @param description The description of the deadline task.
     * @param date The end date and time for the deadline in string format.
     * @throws InvalidDateTimeException If the provided date string is not in a valid format.
     */
    public Deadline(String description, String date) throws InvalidDateTimeException {
        super(description, "D");
        this.date = DateTimeUtils.stringToLocalDateTime(date);
        this.addedTaskDescription();
    }

    /**
     * Returns a formatted string with details specific to the Deadline task.
     *
     * @return A string representation of the deadline details.
     */
    @Override
    public String getDetails() {
        return String.format(" (by: %s)", DateTimeUtils.localDateTimeToString(this.date));
    }

    /**
     * Returns a formatted string suitable for database storage.
     *
     * @return A string representation of the deadline task formatted for database storage.
     */
    public String getDBString() {
        return String.format("%s | %s | %s | %s", "D", this.isDone() ? "1" : "0", this.description,
                DateTimeUtils.localDateTimeToStringForDb(this.date));
    }
}
