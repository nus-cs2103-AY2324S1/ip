package avalon;

import java.time.LocalDateTime;

/**
 * Represents an event task, which is a type of task with a description, completion status,
 * and start and end timestamps.
 */
public class Event extends Task {

    /**
     * The start timestamp for the event task.
     */
    protected LocalDateTime from;

    /**
     * The end timestamp for the event task.
     */
    protected LocalDateTime to;

    /**
     * Creates a new event task with the given description, start timestamp,
     * and end timestamp, and sets its initial completion status to false.
     *
     * @param description The description of the event task.
     * @param from The start timestamp in string format (to be parsed into a LocalDateTime).
     * @param to The end timestamp in string format (to be parsed into a LocalDateTime).
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeParser.stringToDateTime(from);
        this.to = DateTimeParser.stringToDateTime(to);
    }

    /**
     * Returns a string representation of the event task, including its status icon,
     * description, start timestamp, and end timestamp.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.printDateTimeToString(from)
                + " to: " + DateTimeParser.printDateTimeToString(to) + ")";
    }
}
