package DukeTasks;

import java.time.LocalDateTime;

/**
 * Encapsulates the DukeTasks.Event class. Inherits the DukeTasks.Task
 * class and adds on additional unique features.
 *
 * @author Tan Kerway
 */
public class Event extends Task {
    // from date for the event class
    private final LocalDateTime from;
    // to date for the event class
    private final LocalDateTime to;


    /**
     * Constructor for the DukeTasks.Event class.
     *
     * @author Tan Kerway
     * @param description the description of the task
     * @param period the string holding the start and end time
     */
    public Event(String description, String period) {
        super(description);
        int indexTo = period.lastIndexOf("/to");
        // get the String that represents the start time
        this.from = super.parseDate(period.substring(6, indexTo - 1), "from");
        // get the String that represents the end time
        this.to = super.parseDate(period.substring(indexTo + 4), "to");
    }

    /**
     * Constructor for the DukeTasks.Event class.
     *
     * @author Tan Kerway
     * @param description the description of the task
     * @param isDone whether the task is done
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = super.parseDate(from, "from");
        this.to = super.parseDate(to, "to");
    }

    /**
     * Returns the start date of the DukeTasks.Event instance as a String.
     *
     * @author Tan Kerway
     * @return the start date of the DukeTasks.Event instance, as a String
     */
    public String getFrom() {
        return this.from == null ? null : this.from.toString();
    }

    /**
     * Returns the end date of the DukeTasks.Event instance as a String.
     *
     * @author Tan Kerway
     * @return the end date of the DukeTasks.Event instance, as a String
     */
    public String getTo() {
        return this.to == null ? null : this.to.toString();
    }

    /**
     * Returns the String representation of an DukeTasks.Event class.
     *
     * @author Tan Kerway
     * @return the String representation of an DukeTasks.Event class
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.formatDate(this.from) + " to: " + super.formatDate(this.to) + ")";
    }
}