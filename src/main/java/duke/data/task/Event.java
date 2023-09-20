package duke.data.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Event class a child class of Task that
 * has the description, from and to attribute.
 */
public class Event extends Task {
    /** Start date of event */
    protected Date from;
    /** End date of event */
    protected Date to;

    /**
     * Constructor to initialize Event.
     *
     * @param description Description of the event.
     * @param from Start date of event.
     * @param to End date of event.
     */
    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Check whether is there a clash of dates between two events
     *
     * @param event The event that is being compared to
     * @return whether there is a clash or not
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
