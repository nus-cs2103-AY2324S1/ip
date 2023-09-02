package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to generate Events.
 * Extends task.
 */
public class Event extends Task{

    private LocalDate from;
    private LocalDate to;

    /**
     * public constructor for Event.
     * @param val
     * @param from
     * @param to
     */
    public Event (String val, String from, String to){
        super(val);
        this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
