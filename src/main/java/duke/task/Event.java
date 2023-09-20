package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.util.Formatter;

/**
 * Represents an event task, containing a description, from date and to date.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Returns a new Event task containing the message and the from and to dates.
     *
     * @param message The description for the Event.
     * @param from The starting date of the Event.
     * @param to The ending date of the Event.
     */
    public Event(String message, LocalDateTime from, LocalDateTime to) throws DukeException {
        super(message);
        if (from.isBefore(to)) {
            this.from = from;
            this.to = to;
        } else {
            throw new DukeException("Invalid date parameter: From date must be before to date!");
        }
    }

    /**
     * Updates an event task based on the specified update type and value.
     *
     * @param type The UpdateType to update the task with.
     * @param newValue The new value to update the task with.
     * @throws DukeException If the type and new value parameters are invalid.
     */
    @Override
    public void update(UpdateType type, String newValue) throws DukeException {
        switch (type) {
        case DESCRIPTION:
            message = newValue;
            break;
        case DATE1:
            try {
                LocalDateTime newFrom = LocalDateTime.parse(newValue);
                if (newFrom.isBefore(to)) {
                    from = newFrom;
                } else {
                    throw new DukeException("Invalid date parameter: From date must be before to date!");
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("Cannot parse date/time of new event start date!");
            }
            break;
        case DATE2:
            try {
                LocalDateTime newTo = LocalDateTime.parse(newValue);
                if (from.isBefore(newTo)) {
                    to = newTo;
                } else {
                    throw new DukeException("Invalid date parameter: From date must be before to date!");
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("Cannot parse date/time of new event end date!");
            }
            break;
        default:
            throw new DukeException("Invalid update type!");
            // exception thrown, no break statement needed
        }
    }

    /**
     * Returns a String containing information within the Event task, formatted to be saved.
     *
     * @return The event, formatted as a String to be saved in the save file.
     */
    public String toSaveFormatString() {
        return "E | " + getStatusNumber() + " | " + message + " | " + from + " | " + to;
    }

    /**
     * Returns a String representation of the Event task, formatted for output in the application.
     *
     * @return The event, formatted as a String for output in the application.
     */
    public String toString() {
        return "[E]" + getStatusIcon() + " " + message
                + " (from: " + Formatter.formatDateTime(from)
                + " to: " + Formatter.formatDateTime(to) + ")";
    }

    @Override
    public Event clone() {
        assert from.isBefore(to) : "Only a valid Event can be cloned.";
        try {
            return new Event(message, from, to);
        } catch (DukeException e) {
            assert false : "Only a valid Event can be cloned.";
            return null;
        }
    }
}
