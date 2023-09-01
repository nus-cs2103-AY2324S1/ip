package duke.task;

import duke.exceptions.DukeInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class which represents
 * a task that starts and ends at a specific date.
 *
 * @author Andrew Daniel Janong
 */
public class Event extends Task {
    /** Start time of the event */
    protected LocalDate startTime;

    /** End time of the event */
    protected LocalDate endTime;

    /**
     * Creates an Event object.
     *
     * @param description Description of the task.
     * @param startTime Start time of the task.
     * @param endTime End time of the task.
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
     * A string representation of an Event.
     * Uses an extra [E] to represent a Event, start time, and end time.
     *
     * @return the string representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
