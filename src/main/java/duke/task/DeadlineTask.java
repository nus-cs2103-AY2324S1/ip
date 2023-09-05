package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents a task with a deadline, which extends the base `Task` class.
 */
public class DeadlineTask extends Task {
    protected LocalDate byDate;

    /**
     * Constructs a `DeadlineTask` object with a description, deadline date, and completion status.
     *
     * @param description The description of the task.
     * @param byDate      The deadline date in the "YYYY-MM-DD" format.
     * @param isDone      The completion status of the task.
     * @throws DateTimeException If the input date format is invalid.
     */
    public DeadlineTask(String description, String byDate, boolean isDone) throws DukeException {
        super(description, isDone);
        try {
            setDeadlineByDate(byDate);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your date in the YYYY-MM-DD format!");
        }
    }

    /**
     * Sets the deadline date of the task.
     *
     * @param date The deadline date in the "YYYY-MM-DD" format.
     */
    public void setDeadlineByDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.byDate = LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("There is no such data!");
        }
    }

    /**
     * Gets the formatted deadline date.
     *
     * @return The formatted deadline date in "MMM dd yyyy" format.
     */
    public String getFormattedDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return byDate.format(formatter);
    }

    /**
     * Gets the deadline date.
     *
     * @return The deadline date as a `LocalDate` object.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    /**
     * Converts the task to a string for saving to the data file.
     *
     * @return A string representation of the task for saving.
     */
    public String toSave() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }

    /**
     * Returns a string representation of the `DeadlineTask`.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
