package duke.task;

import java.time.LocalDateTime;

import duke.exception.DukeException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by; // The deadline of the task in LocalDateTime format.

    /**
     * Constructs a deadline with the given description and deadline.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the deadline.
     * @throws DukeException If there are problems constructing the deadline.
     */
    public Deadline(String description, String by) throws DukeException {
        this(description, by, false);
    }

    /**
     * Constructs a deadline with the given description, deadline and status.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the deadline.
     * @param isDone      The status of the deadline.
     * @throws DukeException If there are problems constructing the deadline.
     */
    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.getDate(this.by) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.by;
    }
}
