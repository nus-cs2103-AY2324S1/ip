package spot.task;

import spot.exception.SpotException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {

    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs a new Event object.
     *
     * @param description Description of the Event object.
     * @param start Start date of the Event object.
     * @param end End date of the Event object.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Event object.
     *
     * @param description Description of the Event object.
     * @param isDone Boolean representing the state of completion of the Event object.
     * @param start Start date of the Event object.
     * @param end End date of the Event object.
     */
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns String representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Returns representation of the Event object to be stored
     * in a text file within the hard disk.
     *
     * @return Representation of the Event object to be stored.
     */
    @Override
    public String toLine() {
        return " E | " + super.toLine() + " | "
                + this.start.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")) + " | "
                + this.end.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
    }

    /**
     * Returns whether the Event object falls on the specified date.
     *
     * @param date Specified date.
     * @return Boolean representing whether the Event object falls on the specified date.
     */
    @Override
    public boolean fallsOn(LocalDate date) {
        return !date.isAfter(this.end) && !date.isBefore(this.start);
    }

    /**
     * Updates the Event object's deadline.
     *
     * @param deadline Updated deadline.
     * @throws SpotException If the task has no deadline field.
     */
    public void updateDeadline(LocalDate deadline) throws SpotException {
        throw new SpotException("Spot thinks this task doesn't have a deadline!");
    }

    /**
     * Updates the Event object's start date.
     *
     * @param start Updated start date.
     * @throws SpotException If the task has no start date field.
     */
    public void updateStart(LocalDate start) throws SpotException {
        this.start = start;
    }

    /**
     * Updates the Event object's end date.
     *
     * @param end Updated end date.
     * @throws SpotException If the task has no end date field.
     */
    public void updateEnd(LocalDate end) throws SpotException {
        this.end = end;
    }
}
