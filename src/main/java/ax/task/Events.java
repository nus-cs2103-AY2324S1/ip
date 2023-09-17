package ax.task;

import java.time.LocalDate;

/**
 * The Events class which contains info about events
 */
public class Events extends ListItem implements Reminders {

    private LocalDate start;
    private LocalDate end;

    /**
     * Constructor for ax.task.Events.
     */
    public Events(String text, String start, String end) {
        super(text);
        this.start = super.parseDate(start);
        this.end = super.parseDate(end);
    }

    /**
     * Returns string representation of ax.task.Events.
     */
    @Override
    public String toString() {
        return ("[E] " + super.toString()
                + String.format(" (from: %s to: %s)", this.start.toString(), this.end.toString()));
    }

    /**
     * checks if the event start date is today or has passed, and event is not marked as done
     * @return true if event is due and has not been marked, or false otherwise
     */
    public boolean isDue() {
        return super.isDue(this.start) && !this.getDone();
    }

    /**
     * gets the most relevant date for the due date
     * @return Start date of the event
     */
    @Override
    public LocalDate getDueDate() {
        return this.start;
    }
}
