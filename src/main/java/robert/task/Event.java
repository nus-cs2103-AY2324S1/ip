package robert.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import robert.exception.RobertException;

/**
 * An Event variation of the <tt>Task</tt> class. Used to track
 * an event.
 *
 * @author Lee Zhan Peng
 */
public class Event extends Task {

    /** The date that the task begins from */
    private final LocalDate fromDate;

    /** The date that the task ends on */
    private final LocalDate toDate;

    /**
     * Constructs Event using a description, a start date and an end date.
     *
     * @param description the description of the task.
     * @param from the starting date.
     * @param to the ending date.
     * @throws RobertException if to date is before the from date.
     */
    public Event(String description, LocalDate from, LocalDate to) throws RobertException {
        super(description);
        this.fromDate = from;
        this.toDate = to;

        if (this.fromDate.isAfter(this.toDate)) {
            throw new RobertException("The end date of the event is before the start date.\n"
                    + "Please set the dates such that the start date is before/on the end date.");
        }
    }

    /**
     * Constructs Event using a description, a start date, an end date, and an indication
     * on whether it is marked.
     *
     * @param description the description of the task.
     * @param from the starting date.
     * @param to the ending date.
     * @param isDone whether the task is completed.
     * @throws RobertException if to date is before the from date.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) throws RobertException {
        super(description, isDone);
        this.fromDate = from;
        this.toDate = to;

        if (this.fromDate.isAfter(this.toDate)) {
            throw new RobertException("The end date of the event is before the start date.\n"
                    + "Please set the dates such that the start date is before/on the end date.");
        }
    }

    /**
     * A getter of the starting date.
     *
     * @return the start date.
     */
    public LocalDate getFromDate() {
        return this.fromDate;
    }

    /**
     * A getter of the ending date.
     *
     * @return the end date.
     */
    public LocalDate getToDate() {
        return this.toDate;
    }

    /**
     * An identifier on whether the task is happening on a particular date.
     *
     * @return a boolean.
     */
    public boolean isHappeningOn(LocalDate date) {
        return !(this.toDate.isBefore(date) || this.fromDate.isAfter(date));
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
