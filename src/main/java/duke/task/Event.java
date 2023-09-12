package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an Event Task.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor to Event Task.
     *
     * @param description description of task.
     * @param from        task start date.
     * @param to          task end date.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);

        assert from != null;
        assert to != null;

        this.from = from;
        this.to = to;
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

        return this.to.isBefore(date);
    }

    /**
     * Get string representation of task.
     *
     * @return string representation of task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Get a string representing this event to save to file.
     *
     * @return string representing this event to save to file.
     */
    @Override
    public String getSaveString() {
        return String.format("%d event %s /from %s /to %s", getIsDone() ? 1 : 0, getDescription().trim(), from, to);
    }

    /**
     * Check if given object is equal to this object.
     * They are equal if,
     * <ul>
     *     <li>They are the same object</li>
     *     <li>They have the same description, from and to time</li>
     * </ul>
     *
     * @param obj the object to be compared.
     * @return true if equal, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Event) {
            Event d = (Event) obj;
            return Objects.equals(this.getDescription(), d.getDescription())
                    && this.from == d.from
                    && this.to == d.to;
        } else {
            return false;
        }
    }
}
