package ax.task;

import java.time.LocalDate;

/**
 * The Deadlines class contains information about deadlines
 */
public class Deadlines extends ListItem implements Reminders {

    private LocalDate end;

    /**
     * Constructor for ax.task.Deadlines.
     */
    public Deadlines(String text, String end) {
        super(text);
        this.end = super.parseDate(end);
    }

    /**
     * Returns string representation of ax.task.Deadlines.
     */
    @Override
    public String toString() {

        return "[D] " + super.toString() + String.format(" (by: %s)", this.end.toString());
    }

    /**
     * checks if the event due date is today or has passed, and event is not marked as done
     * @return true if event is due and has not been marked, or false otherwise
     */
    public boolean isDue() {
        return super.isDue(this.end) && !this.getDone();
    }

    /**
     * gets the most relevant date for the due date
     * @return end date of the dateline
     */
    @Override
    public LocalDate getDueDate() {
        return this.end;
    }

}
