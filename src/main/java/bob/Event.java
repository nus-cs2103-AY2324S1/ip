package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that contains a description, starting date and ending date
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for the Event class
     *
     * @param description the name/description of the event
     * @param from the starting date & time of the event
     * @param to the ending date & time of the event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFrom() {
        return from.toString();
    }

    @Override
    public String getTo() {
        return to.toString();
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the string representation of this event, including its type of task, completion status,
     * description, and duration (date & time)
     *
     * @return the string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
