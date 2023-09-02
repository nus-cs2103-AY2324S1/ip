package fluke.tasks;

import fluke.exceptions.FlukeException;
import fluke.exceptions.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * An event refers to a task which has a starting "from" date and an ending "to" date.
 */
public class Event extends Task {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    /**
     * Date which this event begins.
     */
    protected LocalDate from;

    /**
     * Date which this event ends.
     */
    protected LocalDate to;

    /**
     * Constructs an Event with a description, from date and to date.
     * @param description Description of the event.
     * @param from Beginning date of the event, in format YYYY-MM-DD.
     * @param to Ending date of the event, in format YYYY-MM-DD
     * @throws FlukeException when the description or the dates given are invalid.
     */
    public Event(String description, String from, String to) throws FlukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
            if (this.from.isAfter(this.to)) {
                throw new InvalidInputException();
            }
        } catch (DateTimeParseException d) {
            throw new InvalidInputException();
        }

    }

    /**
     * Constructs an Event with a description, from date and to date.
     * @param description Description of the event.
     * @param isDone Whether the event is over.
     * @param from Beginning date of the event, in format YYYY-MM-DD.
     * @param to Ending date of the event, in format YYYY-MM-DD
     * @throws FlukeException when the description or the dates given are invalid.
     */
    public Event(String description, boolean isDone, String from, String to) throws FlukeException {
        super(description, isDone);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * String representation of an event.
     * @return a String representation of the event, containing its description and starting and ending dates.
     */
    @Override
    public String toString() {
        String fromString = from.format(DATE_TIME_FORMATTER);
        String toString = to.format(DATE_TIME_FORMATTER);
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
