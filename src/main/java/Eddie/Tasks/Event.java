package Eddie.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event Task with a from date and to date.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for Event class.
     * @param name Description for the event.
     * @param startDate When the Event starts.
     * @param endDate When the Event ends.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the type of Task.
     * @return String of the type to be printed.
     */
    public String getType() {
        return "E";
    }

    /**
     * Returns the start date of the Event
     * @return The date formatted in MMM d yyyy.
     */
    public String getStartDate() {
        return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the end date of the Event.
     * @return The date formatted in MMM d yyyy.
     */
    public String getEndDate() {
        return endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the full description of the task to be listed.
     * @return Type, status, description, start date, end date.
     */
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatus() + "]" + this.getName()
                + " (from: " + this.getStartDate() + " to: " + this.getEndDate() + ")";
    }
}
