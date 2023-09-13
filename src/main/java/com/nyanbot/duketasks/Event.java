package com.nyanbot.duketasks;

import java.time.LocalDateTime;

/**
 * Encapsulates the DukeTasks.Event class. Inherits the DukeTasks.Task
 * class and adds on additional unique features.
 *
 * @author Tan Kerway
 */
public class Event extends Task {
    private final LocalDateTime from;
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
        this.from = super.dukeTimeParser.parseDate(period.substring(6, indexTo - 1), "from");
        this.to = super.dukeTimeParser.parseDate(period.substring(indexTo + 4), "to");
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
        this.from = super.dukeTimeParser.parseDate(from, "from");
        this.to = super.dukeTimeParser.parseDate(to, "to");
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
        return "[E]"
                + super.toString()
                + " (from: " + super.dukeTimeParser.formatDate(this.from)
                + " to: " + super.dukeTimeParser.formatDate(this.to) + ")";
    }
}
