package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor to Event Task.
     *
     * @param description description of task
     * @param from        task start date
     * @param to          task end date
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns if task is before given date.
     *
     * @param date given date to check against
     * @return true if task is before given date, false otherwise
     */
    @Override
    public boolean isBefore(LocalDate date) {
        return this.to.isBefore(date);
    }

    /**
     * Get string representation of task.
     *
     * @return string representation of task
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
     * @return string representing this event to save to file
     */
    @Override
    public String getSaveString() {

        return String.format("%d event %s /from %s /to %s", isDone ? 1 : 0, description.trim(), from, to);
    }
}
