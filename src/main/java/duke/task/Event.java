package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.InvalidTimeException;
/**
 * Represents the Event of a task.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class Event extends Task {
    protected LocalDate starting;
    protected LocalDate ending;

    /**
     * Constructs an Event with a specified description and date.
     *
     * @param description A string describing the Event task.
     * @throws DukeException If the deadline date is not a valid date.
     */
    public Event(String description) throws DukeException {
        super(description.substring(0, description.indexOf("/from") - 1));
        int fromIndex = description.indexOf("/from");
        String tempStarting = description.substring(fromIndex + 6, fromIndex + 16);
        int toIndex = fromIndex + 21;
        try{
            this.starting = LocalDate.parse(tempStarting);
            this.ending = LocalDate.parse(description.substring(toIndex));
            if (this.starting.isAfter(this.ending)) {
                throw new InvalidTimeException("The starting time could not pass the ending time");
            }
        }
        catch (DateTimeParseException e) {
            throw new InvalidTimeException("Invalid input of Date");
        }
    }

    /**
     * Returns a String representing the Event that will be stored.
     *
     * @return the string representing the Event that will be stored.
     */
    @Override
    public String getInput() {
        return "E | " + this.getStatusIcon() + " | " + this.description + " | " + this.starting + " | " + this.ending;
    }

    /**
     * Returns the String representation of an Event.
     *
     * @return the String representation of an Event.
     */
    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.description +
                " (from: " + this.starting.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + this.ending.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
