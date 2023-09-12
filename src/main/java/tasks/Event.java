package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a task that has a duration of time which it has to be done.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor of Event class.
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * This method returns the dates and/or times that the event is due to take place
     * in the format specified.
     * @return the dates and/or time of the event.
     */
    public String getFromTo() {
        return from.toString()
                + " | "
                + to.toString();
    }

    /**
     * This method returns the from date of the event as a String.
     * @return the String representation of the from date.
     */
    public String getFrom() {
        return from.toString();
    }

    /**
     * This method returns the to date of the event as a String.
     * @return the String representation of the to date.
     */
    public String getTo() {
        return to.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(from:" + " "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
