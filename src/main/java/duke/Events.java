package duke;

/**
 * Represents an event task with a specified start and end time.
 */
public class Events extends Task {
    /** The raw string representing the starting time of the event. */
    protected String start;

    /** The raw string representing the ending time of the event. */
    protected String end;

    /** The parsed DateTime object representation of the event's start time. */
    protected DateTime dtStart;

    /** The parsed DateTime object representation of the event's end time. */
    protected DateTime dtEnd;

    /**
     * Initializes an Events task with the given description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param start The starting time of the event.
     * @param end The ending time of the event.
     */
    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.dtStart = new DateTime(start);
        this.dtEnd = new DateTime(end);
    }

    /**
     * Returns the formatted string used for saving this event task to storage.
     *
     * @return The formatted string representation for saving.
     */
    @Override
    public String getSavingFormat() {
        return "[E] | [" + getStatusIcon() + "] | "
                + description + " | " + start + " | " + end;
    }

    /**
     * Returns the string representation of this event task.
     *
     * @return The formatted string representation.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description
                + " (from: " + dtStart + ", to: " + dtEnd + ")";
    }
}
