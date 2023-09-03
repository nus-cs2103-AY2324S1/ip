package task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline is a Task that has a date/time which this task should be completed by.
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;
    protected String by;

    /**
     * The constructor of Deadline.
     *
     * @param description the deadline description.
     * @param byDate the date of the deadline.
     * @param byTime the time of the deadline.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
        this.by = byDate.toString() + " " + byTime.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
