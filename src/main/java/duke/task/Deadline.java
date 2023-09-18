package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor to Deadline Task.
     *
     * @param description description of task.
     * @param by          date task is due.
     */
    public Deadline(String description, LocalDate by) {
        super(description);

        assert by != null;

        this.by = by;
    }

    /**
     * Returns if task is before given date.
     *
     * @param date given date to check against.
     * @return true if task is before given date, false otherwise.
     */
    @Override
    public boolean isBefore(LocalDate date) {
        assert date != null;
        return this.by.isBefore(date);
    }

    /**
     * Gets string representation of task.
     *
     * @return string representation of task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Gets a string representing this event to save to file.
     *
     * @return string representing this event to save to file.
     */
    @Override
    public String getSaveString() {
        return String.format("%d deadline %s /by %s", getIsDone() ? 1 : 0, getDescription().trim(), by);
    }

    /**
     * Checks if given object is equal to this object.
     * They are equal if,
     * <ul>
     *     <li>They are the same object.</li>
     *     <li>They have the same description and by time.</li>
     * </ul>
     *
     * @param obj the object to be compared.
     * @return true if equal, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            return Objects.equals(this.getDescription(), d.getDescription()) && this.by == d.by;
        } else {
            return false;
        }
    }
}
