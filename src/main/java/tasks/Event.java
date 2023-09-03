package tasks;

import tasks.Task;

import java.time.LocalDateTime;

/**
 * The `Event` class represents a task that occurs during a specific time period.
 * It extends the `Task` class and includes a start time and end time for the event.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an `Event` object with the specified description and time period.
     *
     * @param description The description of the event.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the `Event` object to a string in a save file format.
     *
     * @return A string representation of the `Event` object in save file format.
     */
    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + " | " + this.from + "-" + this.to;
    }

    /**
     * Returns a string representation of the `Event` object for displaying to the user.
     *
     * @return A string representation of the `Event` object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(outputFormatter) + " to: " + to.format(outputFormatter) + ")";
    }
}

