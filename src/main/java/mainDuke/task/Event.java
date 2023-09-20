package mainDuke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mainDuke.exceptions.DukeException;

/**
 * Represents a Task of Event type, has a <code>to</code> and <code>from</code>
 * <code>to</code> and <code>from</code> is stored in <code>LocalDate</code> format.
 */
public class Event extends Task {
    /**
     * When the event starts.
     */
    private final LocalDate from;
    /**
     * When the event ends.
     */
    private final LocalDate to;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    /**
     * Constructor for Event, returns an Event.
     * @param done whether the event is done or over.
     * @param desc name of the event, when is it from, indicated by /from and when is it to
     *        , indicated by <code>to</code>. Both must be in YYYY-MM-DD format.
     * @throws DukeException throws an error if the date is unable to be parsed, likely due
     *       to wrong format.
     */
    public Event(boolean done, String desc) throws DukeException {
        super(done, desc.substring(6, desc.indexOf("/from")));
        int fromIndex = desc.indexOf("/from");
        int toIndex = desc.indexOf("/to");

        try {
            String fromString = desc.substring(fromIndex + 6, toIndex).replace(" ", "");
            String toString = desc.substring(toIndex + 4).replace(" ", "");

            this.from = LocalDate.parse(fromString);
            this.to = LocalDate.parse(toString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("You didn't enter the event in the correct format!");
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format! Use eg.2019-12-02");
        }
    }

    /**
     * Getter for from date.
     * @return LocalDate from.
     */
    public LocalDate getFrom() {
        return this.from;
    }
    /**
     * Getter for to date.
     * @return LocalDate to.
     */
    public LocalDate getTo() {
        return this.to;
    }
    /**
     * String representation of Event, including task type, if task is done, from and to date, and task name.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n(from: " + this.from.format(formatter)
                + " to: " + this.to.format(formatter) + ")";
    }
}
