package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by; // The deadline of the task in LocalDateTime format.

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

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