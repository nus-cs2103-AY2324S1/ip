package linus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import linus.exception.LinusException;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected String type = "linus.task.Event";
    protected LocalDate from = null;
    protected LocalDate to = null;

    /**
     * Constructs an Event object with the specified description, start date and end date.
     *
     * @param description The description of the event.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     * @throws LinusException
     */
    public Event(String description, String from, String to) throws LinusException {
        super(description);
        setDate(from, to);
    }

    /**
     * Sets the start date and end date of the event.
     *
     * @param from
     * @param to
     * @throws LinusException
     */
    private void setDate(String from, String to) throws LinusException {
        try {
            LocalDate parsedFromDate = LocalDate.parse(from);
            LocalDate parsedToDate = LocalDate.parse(to);

            if (parsedFromDate.isAfter(parsedToDate)) {
                throw new LinusException(
                        "☹ OOPS!!! Please specify the start date "
                                + "before/on the same day as the end date."
                );
            }

            this.from = parsedFromDate;
            this.to = parsedToDate;

        } catch (DateTimeParseException e) {
            throw new LinusException(
                    "☹ OOPS!!! Please specify the start and/or end dates "
                            + "in the correct format: yyyy-mm-dd"
            );
        }

    }

    /**
     * Returns the icon representing the type of task.
     *
     * @return The icon representing the type of task.
     */
    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    /**
     * Returns a String representation of the Event object.
     *
     * @return A String representation of the Event object.
     */
    @Override
    public String toString() {
        assert this.from != null && this.to != null : "Event object should have non-null start and end dates.";

        return super.toString()
                + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
