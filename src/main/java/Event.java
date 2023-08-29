import java.time.LocalDate;

/**
 * Event class representing a task and description
 */
public class Event extends Task{

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for the Event class
     * @param description Description of the task
     * @param from start time of task
     * @param to end time of task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDates(from);
        this.to = parseDates(to);
    }

    /**
     * String representation of the Event task
     * @return Information of event
     */
    @Override
    public String toString() {
        String ret = "[E] " + super.toString() + " (from: " + printDates(this.from) + " to: " + printDates(this.to) + ")" ;
        return ret;
    }
}
