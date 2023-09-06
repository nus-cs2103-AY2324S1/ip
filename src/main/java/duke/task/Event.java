package duke.task;

import java.time.LocalDateTime;

import duke.exception.DukeException;

/**
 * Represents a task with a start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from; // The start time of the task in LocalDateTime format.
    protected LocalDateTime to; // The end time of the task in LocalDateTime format.

    /**
     * Constructs an event with the given description, start time and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @throws DukeException If there are problems constructing the event.
     */
    public Event(String description, String from, String to) throws DukeException {
        this(description, from, to, false);
    }

    /**
     * Constructs an event with the given description, start time, end time and status.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isDone      The status of the event.
     * @throws DukeException If there are problems constructing the event.
     */
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
