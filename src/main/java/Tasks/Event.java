package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a task that has a duration of time which it has to be done.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

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
        return from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:"
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "to:"
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}