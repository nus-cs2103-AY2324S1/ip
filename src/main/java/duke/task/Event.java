package duke.task;

import duke.exceptions.DukeInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that starts at a specific date/time and ends at a specific date/time.
 *
 * @author Andrew Daniel Janong
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected LocalDate startTime;

    /**
     * The end time of the event.
     */
    protected LocalDate endTime;

    /**
     * A public constructor for the task.Event.
     * @param description
     * @param startTime
     * @param endTime
     */
    public Event(String description, String startTime, String endTime) throws  DukeInvalidDateException {
        super(description);
        try {
            this.startTime = LocalDate.parse(startTime);
            this.endTime = LocalDate.parse(endTime);
        } catch (DateTimeParseException error) {
            throw new DukeInvalidDateException("Date must be of the form yyyy-mm-dd.");
        }

    }

    @Override
    public String toDataRepresentation() {
        return "E|" + super.toDataRepresentation() + "|" + startTime + "|" + endTime;
    }


    /**
     * A string representation of a task.Event.
     * Uses an extra [E] to represent a task.Event, start time, and end time.
     * @return the string representation of the task.Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
