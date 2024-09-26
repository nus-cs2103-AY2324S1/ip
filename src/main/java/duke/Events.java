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

        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        assert start != null && !start.trim().isEmpty() : "Start time should not be null or empty";
        assert end != null && !end.trim().isEmpty() : "End time should not be null or empty";

        this.start = start;
        this.end = end;
        this.dtStart = new DateTime(start);
        this.dtEnd = new DateTime(end);
    }

    /**
     * Initializes an Events task with the given description, start time, end time, completion status, and notes.
     *
     * @param description The description of the event task.
     * @param start The starting time of the event.
     * @param end The ending time of the event.
     * @param isDone The completion status of the event task.
     * @param notes Additional notes associated with the event task.
     */
    public Events(String description, String start, String end, Boolean isDone, String notes) {
        super(description, isDone, notes);

        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        assert start != null && !start.trim().isEmpty() : "Start time should not be null or empty";
        assert end != null && !end.trim().isEmpty() : "End time should not be null or empty";

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
        // Assert that all necessary data members are not null before formatting.
        assert start != null && !start.trim().isEmpty() : "Start time should not be null or empty when saving";
        assert end != null && !end.trim().isEmpty() : "End time should not be null or empty when saving";

        return "[E] | [" + getStatusIcon() + "] | "
                + description + " | " + start + " | " + end + " | " + getNotes();
    }

    /**
     * Converts a split string array into an Events object.
     * This method is typically used for parsing saved data from storage to recreate an Events task.
     *
     * @param split The split string array containing the task data.
     * @return An Events object constructed from the provided split string.
     */
    public static Events toTask(String[] split) {
        return new Events(split[2], split[3], split[4], split[1].contains("[X]"), split.length == 6 ? split[5] : "");
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
