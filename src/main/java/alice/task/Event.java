package alice.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import alice.exception.DukeException;

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

    /**
     * Constructs an event with the given argument.
     *
     * @param argument The argument from the command.
     * @throws DukeException If there are problems constructing the event.
     */
    public static Event fromArgument(String argument) throws DukeException {
        try {
            String[] inputs = argument.split(" /", 3);
            String description = inputs[0];
            String from = inputs[1].split(" ", 2)[1];
            String to = inputs[2].split(" ", 2)[1];
            return new Event(description, from, to);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The start time or end time of an event cannot be empty.");
        } catch (DateTimeParseException e) {
            throw new DukeException(Task.DATE_TIME_FORMAT_PARSING_ERROR_MESSAGE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Task.getDate(this.from) + " to: " + Task.getDate(this.to) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.from + " | " + this.to;
    }
}
