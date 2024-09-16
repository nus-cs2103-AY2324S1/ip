package arona.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. A deadline task has a description and a date for the deadline.
 */
public class DeadlineTask extends Task {
    protected LocalDate date;

    /**
     * Constructs a new deadline task with the given description and date.
     *
     * @param description The description of the deadline task.
     * @param date        The date of the deadline.
     */
    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructs a new deadline task with the given description, date, and marked status.
     *
     * @param description The description of the deadline task.
     * @param date        The date of the deadline.
     * @param isMarked    The marked status of the task (0 for unmarked, 1 for marked).
     */
    public DeadlineTask(String description, LocalDate date, int isMarked) {
        super(description, isMarked);
        this.date = date;
    }

    /**
     * Gets the date of the deadline task.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Gets a string representation of the deadline task.
     *
     * @return A string containing the task's status icon, description, and formatted date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

}
