package duke.task;

import duke.Duke;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a `Deadline` task with the specified name and deadline.
     *
     * @param name The name of the task.
     * @param by   The deadline for the task in "dd-MM-yyyy HHmm" format.
     * @throws DukeException If the date and time format is incorrect.
     */
    public Deadline(String name, String by) throws DukeException {
        super(name);
        try {
            this.by = LocalDateTime.parse(by, Duke.DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.");
        }
    }

    /**
     * Constructs a `Deadline` task with the specified name, deadline, and
     * completion status.
     *
     * @param name   The name of the task.
     * @param by     The deadline for the task in "dd-MM-yyyy HHmm" format.
     * @param isDone The completion status of the task.
     */
    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the `Deadline` task, including its type,
     * name,
     * and deadline.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the `Deadline` task in a format suitable
     * for data storage.
     *
     * @return A string representation of the task for data storage.
     */
    @Override
    public String toDataString() {
        return "D|" + super.toDataString() + "|" + by;
    }
}
