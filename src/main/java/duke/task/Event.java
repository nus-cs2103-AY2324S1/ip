package duke.task;

import duke.Duke;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that is an event with a specific start and end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an `Event` task with the specified name, start time, and end time.
     *
     * @param name The name of the event.
     * @param from The start time of the event in "dd-MM-yyyy HHmm" format.
     * @param to   The end time of the event in "dd-MM-yyyy HHmm" format.
     * @throws DukeException If the date and time format is incorrect.
     */
    public Event(String name, String from, String to) throws DukeException {
        super(name);
        try {
            this.from = LocalDateTime.parse(from, Duke.DATETIME_INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, Duke.DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.");
        }
    }

    /**
     * Constructs an `Event` task with the specified name, start time, end time, and
     * completion status.
     *
     * @param name   The name of the event.
     * @param from   The start time of the event in "dd-MM-yyyy HHmm" format.
     * @param to     The end time of the event in "dd-MM-yyyy HHmm" format.
     * @param isDone The completion status of the event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the `Event` task, including its type,
     * name,
     * and event period.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the `Event` task in a format suitable for
     * data storage.
     *
     * @return A string representation of the task for data storage.
     */
    @Override
    public String toDataString() {
        return "E|" + super.toDataString() + "|" + from + "|" + to;
    }
}
