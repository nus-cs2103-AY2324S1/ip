package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.util.Keyword;
import duke.util.Storage;
import duke.util.Time;

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
        return String.format("D%s%s%s%s\n",
                Storage.SEPARATOR,
                super.fileFormat(),
                Storage.SEPARATOR,
                by.format(Time.DATE_TIME_FORMATTER));
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
        assert key != null : "Keyword cannot be null";
        assert date != null : "Date cannot be null";

        LocalDate byDate = by.toLocalDate();
        boolean isDeadline = key.equals(Keyword.DEADLINE);
        boolean isOnOrAfterDate = byDate.isAfter(date) || byDate.equals(date);
        return isDeadline && isOnOrAfterDate;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                by.format(Time.DATE_TIME_DISPLAY_FORMATTER));
    }

    /**
     * Compares the current object with the given object, it returns true
     * if the given object is a deadline task and has the same description.
     *
     * @param obj Object to be compared.
     * @return The result of comparison.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int compareDeadline(Task task) {
        int smaller = -1;
        if (task instanceof Todo) {
            return smaller;
        }
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return this.by.compareTo(deadline.by);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return this.by.compareTo(event.to);
        } else {
            return 0;
        }
    }

    @Override
    public int compareType(Task task) {
        int smaller = -1;
        int larger = 1;
        int equal = 0;
        if (task instanceof Deadline) {
            return super.compareTo(task);
        }
        if (task instanceof Todo) {
            return larger;
        } else if (task instanceof Event) {
            return smaller;
        } else {
            return equal;
        }
    }
}
