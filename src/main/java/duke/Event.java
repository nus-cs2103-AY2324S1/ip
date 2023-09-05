package duke;

/**
 * Represents a <code>Event</code> object that extends from <code>Task</code>.
 * An <code>Event</code> object contains a description, a start time, and an end time.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Constructs a new <code>Event</code> object.
     * @param description the description of the event.
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the description of the <code>Event</code> object.
     * @return String representation of the Event object to be displayed.
     */
    @Override
    public String getDescription() {
        return "[E] " + super.getDescription()
                + " (from:" + this.startTime
                + " to:" + this.endTime + ")";
    }

    /**
     * Gets the description of the <code>Event</code> object.
     * @return String representation of the <code>Event</code> object to be saved to the task list file.
     */
    @Override
    public String getSavedString() {
        return "E " + super.getSavedString() + " | " + this.startTime + "-" + this.endTime;
    }

}
