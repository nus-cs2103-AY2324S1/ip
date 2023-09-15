package zean.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import zean.exception.ZeanException;

/**
 * The class that represents an event task.
 *
 * @author Zhong Han
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructor for the event task.
     *
     * @param bool The completion status of the event task.
     * @param description The description of the event task.
     * @param from The due date of the deadline task in the format YYYY-MM-DD.
     * @param to The due date of the deadline task in the format YYYY-MM-DD.
     */
    public Event(String bool, String description, String from, String to) throws ZeanException {
        super(bool, description.strip());
        assert description != null;
        try {
            this.from = LocalDate.parse(from.strip());
            this.to = LocalDate.parse(to.strip());
            if (this.to.isBefore(this.from)) {
                throw new ZeanException("The end date is before the start date!");
            }
        } catch (DateTimeParseException e) {
            throw new ZeanException("The date is invalid!");
        }
    }

    /**
     * Updates the date of the event task. Depending on what type of task it is, error is thrown
     * when the task does not support the updating of a particular date.
     *
     * @param by An empty string, else an error is thrown.
     * @param from The new start date for event task.
     * @param to The new end date for event task.
     */
    public void updateDates(String by, String from, String to) {
        if (!by.isBlank()) {
            throw new ZeanException("Cannot update by date for event task!");
        }
        LocalDate fromDate = this.from;
        LocalDate toDate = this.to;
        if (!to.isBlank()) {
            try {
                toDate = LocalDate.parse(to.strip());
            } catch (DateTimeParseException e) {
                throw new ZeanException("The date is invalid!");
            }
            if (fromDate.isAfter(toDate)) {
                throw new ZeanException("The new end date is before the start date!");
            }
        }
        if (!from.isBlank()) {
            try {
                fromDate = LocalDate.parse(from.strip());
            } catch (DateTimeParseException e) {
                throw new ZeanException("The date is invalid!");
            }
            if (toDate.isBefore(fromDate)) {
                throw new ZeanException("The new start date is after the end date!");
            }
        }
        this.from = fromDate;
        this.to = toDate;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return A string comprising the symbol, status,
     *      description, start and end time/date of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }

    /**
     * Returns the string representing the task to be written in the disk.
     *
     * @return The string describing this task to be written in the disk.
     */
    @Override
    public String toStringForFile() {
        return "E | " + super.toStringForFile() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns the string with formatted start date of the event.
     *
     * @return The string with formatted start date of the event.
     */
    protected String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the string with formatted end date of the event.
     *
     * @return The string with formatted end date of the event.
     */
    protected String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
