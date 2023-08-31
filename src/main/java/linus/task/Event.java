package linus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import linus.exception.LinusException;

public class Event extends Task {
    protected String type = "linus.task.Event";
    protected LocalDate from = null;
    protected LocalDate to = null;

    /**
     * Constructs an Event object with the specified description, start date and end date.
     * @param description The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @throws LinusException
     */
    public Event(String description, String from, String to) throws LinusException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);

            if (this.from.isAfter(this.to)) {
                throw new LinusException("☹ OOPS!!! Please specify the start date before/on the same day as the end date.");
            }
        } catch (DateTimeParseException e) {
            throw new LinusException("☹ OOPS!!! Please specify the start and/or end dates in the correct format: yyyy-mm-dd");
        }
    }

    /**
     * Returns the icon representing the type of task.
     * @return String
     */
    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    /**
     * Returns a String representation of the Event object.
     * @return String
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
