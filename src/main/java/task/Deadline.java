package task;

import java.time.LocalDateTime;

import utils.DateTimeFormat;

/**
 * Deadline is a Task that has a date/time which this task should be completed by.
 */
public class Deadline extends Task {
    protected String byString;

    /**
     * The constructor of Deadline.
     *
     * @param description The deadline description.
     * @param byString The string representation of the by datetime.
     */
    public Deadline(String description, String byString) {
        super(description);
        this.byString = byString;
    }

    /**
     * The constructor of Deadline with specified status.
     *
     * @param description The deadline description.
     * @param byString The string representation of the by datetime.
     * @param isDone The status of the deadline.
     */
    public Deadline(String description, String byString, boolean isDone) {
        super(description, isDone);
        this.byString = byString;
    }

    /**
     * To get the date and time of the deadline.
     *
     * @return The LocalDateTime object of the date and time of the deadline.
     */
    public LocalDateTime getByDateTime() {
        return DateTimeFormat.toLocalDateTime(this.byString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }
}
