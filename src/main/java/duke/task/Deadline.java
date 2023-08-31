package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.Keyword;
import duke.Storage;
import duke.Time;

/**
 * Represents a deadline task. A <code>Deadline</code> object corresponds to
 * a task with a description and a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a <code>Deadline</code> object with the given description and
     * deadline.
     *
     * @param description The description of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the file format of the deadline task.
     *
     * @return File format of the deadline task.
     */
    @Override
    public String fileFormat() {
        return String.format("D%s%s%s%s",
                Storage.SEPARATOR, super.fileFormat(),
                Storage.SEPARATOR, this.by.format(Time.DATE_TIME_FORMATTER));
    }

    /**
     * Checks if the deadline task is on or before the given date. Returns true if
     * the given date is on or before the deadline of the deadline task.
     * Otherwise, returns false.
     *
     * @param key Keyword to check if it is the right type of task.
     * @param date The date to check if the task is on or before.
     * @return Whether the deadline task is on or before the given date.
     */
    @Override
    public boolean onDate(Keyword key, LocalDate date) {
        LocalDate by = this.by.toLocalDate();
        return key.equals(Keyword.DEADLINE)
                && (by.isAfter(date) || by.equals(date));
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(Time.DATE_TIME_DISPLAY_FORMATTER));
    }
}
