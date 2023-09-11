package chatbot.alain;

import java.time.LocalDate;

/**
 * Represents a task that is an event occurring within a specific time range.
 */
public class Events extends Task {

    /**
     * The starting time of the event.
     */
    protected LocalDate from;
    /**
     * The starting time (into String) of the event.
     */
    protected String fromString;
    /**
     * The ending time of the event.
     */
    protected LocalDate to;
    /**
     * The ending time (into String) of the event.
     */
    protected String toString;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the event.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Events(String description, String from, String to) {
        super(description);
        this.fromString = ChatbotAlain.stringToTimeString(this, from, false);
        this.toString = ChatbotAlain.stringToTimeString(this, to, true);
        if (this.from == null) {
            this.from = LocalDate.MIN;
        }
        if (this.to == null) {
            this.to = LocalDate.MAX;
        }
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + fromString + " to: " + toString + ")";
    }
    @Override
    public void setTime(LocalDate date, boolean by) {
        if (by) {
            this.to = date;
        } else {
            this.from = date;
        }
    }
    @Override
    public LocalDate getDate() {
        return this.to;
    }
}


