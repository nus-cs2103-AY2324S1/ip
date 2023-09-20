package duke.data.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Event class is a child class of Task that represents tasks
 * with a description and specific start and end date.
 */
public class Event extends Task {
    /** The start date of the event. */
    protected Date from;
    /** The end date of the event. */
    protected Date to;

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Checks whether there is a clash of dates between two events.
     *
     * @param event The event being compared for date clash.
     * @return True if there is a clash, false otherwise.
     */
    public boolean checkClash(Event event) {
        if (this.from.compareTo(event.from) >= 0 && this.from.compareTo(event.to) <= 0) {
            return true;
        } else if (this.to.compareTo(event.from) >= 0 && this.to.compareTo(event.to) <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toWrite() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        return "E | " + super.toWrite() + " | " + formatter.format(from) + "-" + formatter.format(to) + "\n";
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + formatter.format(from) + " to: " + formatter.format(to) + ")";
    }
}
