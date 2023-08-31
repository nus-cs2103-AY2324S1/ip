package adam.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to create Event objects that holds the description and when the event starts and ends.
 */
public class Events extends Task implements Serializable {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Initializes the dates for when the event starts and when it ends.
     *
     * @param text Text that gives the description.
     * @param from From when the event starts.
     * @param to To when the event ends.
     */
    public Events(String text, String from, String to){
        super(text);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
