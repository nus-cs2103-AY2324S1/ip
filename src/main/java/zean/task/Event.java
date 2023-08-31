package zean.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import zean.exception.DukeException;

/**
 * The class that represents an event task.
 *
 * @author Zhong Han
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructor for the event task.
     *
     * @param description The description of the event task.
     * @param from The start time/date of the event task.
     * @param to The end time/date of the event task.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description.strip());
        try {
            this.from = LocalDate.parse(from.strip());
            this.to = LocalDate.parse(to.strip());
            if (this.to.isBefore(this.from)) {
                throw new DukeException("\tThe end date is before the start date!");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("\tThe date is invalid!");
        }
    }

    /**
     * Constructor for the event task.
     *
     * @param bool The completion status of the event task.
     * @param description The description of the event task.
     */
    public Event(String bool, String description, String from, String to) {
        super(description.strip());
        this.from = LocalDate.parse(from.strip());
        this.to = LocalDate.parse(to.strip());
        if (Integer.parseInt(bool) == 1) {
            this.isDone = true;
        }
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
