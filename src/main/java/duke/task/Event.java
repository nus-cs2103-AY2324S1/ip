package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;

/**
 * Represents a task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from; // The start time of the task in LocalDateTime format.
    protected LocalDateTime to; // The end time of the task in LocalDateTime format.

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    public Event(String description, String from, String to, boolean isDone) throws DukeException {
        super(description, isDone);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Task.getDate(this.from) + " to: " + Task.getDate(this.to) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.from + " | " + this.to;
    }
}
