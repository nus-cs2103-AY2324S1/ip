package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;



/**
 * Represents a task with a deadline, which extends the base `Task` class.
 */
public class DeadlineTask extends Task {
    protected Type type = Type.DEADLINE;
    protected LocalDate byDate;

    /**
     * Constructs a `DeadlineTask` object with a description, deadline date, and completion status.
     *
     * @param description The description of the task.
     * @param by      The deadline date in the "YYYY-MM-DD" format.
     * @param isDone      The completion status of the task.
     * @throws DateTimeException If the input date format is invalid.
     */
    public DeadlineTask(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        assert description != null : "Description cannot be null.";
        try {
            byDate = setDate(by);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your date in the YYYY-MM-DD format!");
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

    public String toSave() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public LocalDate getCompareDate() {
        return byDate;
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
