package tasks;

import java.time.LocalDateTime;

import duke.DukeException;

/**
 * Represents an event task, which extends the Task class.
 * An Event task is characterized by a description and a specified duration,
 * marked by its start (from) and end (to) date/times.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates a new Event task with the specified description, start and end times.
     *
     * @param description The description or title of the event.
     * @param from The starting date/time of the event given in string format.
     * @param to The ending date/time of the event given in string format.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = stringToDate(from);
        this.to = stringToDate(to);
    }

    /**
     * Retrieves the starting date/time of this event.
     *
     * @return The LocalDateTime representing the starting date/time.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Retrieves the ending date/time of this event.
     *
     * @return The LocalDateTime representing the ending date/time.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns the string representation of this Event task, which includes the task type identifier "[E]",
     * marking status icon, task description, and the duration of the event.
     *
     * @return The string representation of this Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateToString(from) + " to: " + dateToString(to) + ")";
    }
}
