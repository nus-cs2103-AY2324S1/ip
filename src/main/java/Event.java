/**
 * Encapsulates the Event class. Inherits the Task
 * class and adds on additional unique features.
 *
 * @author Tan Kerway
 */
public class Event extends Task {
    // from date for the event class
    private final String from;
    // to date for the event class
    private final String to;


    /**
     * Constructor for the Event class.
     *
     * @author Tan Kerway
     * @param description the description of the task
     * @param period the string holding the start and end time
     */
    public Event(String description, String period) {
        super(description);
        int indexTo = period.lastIndexOf("/to");
        // get the String that represents the start time
        this.from = period.substring(6, indexTo - 1);
        // get the String that represents the end time
        this.to = period.substring(indexTo + 4);
    }

    /**
     * Returns the String representation of an Event class.
     *
     * @author Tan Kerway
     * @return the String representation of an Event class
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}