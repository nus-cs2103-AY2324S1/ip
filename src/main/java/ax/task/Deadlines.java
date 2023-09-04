package ax.task;

import java.time.LocalDate;

/**
 * The Deadlines class contains information about deadlines
 */
public class Deadlines extends ListItem {

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
}
