/**
 * Encapsulates an Event task. Event tasks have a start time, end time and description.
 */
public class Event extends Task {

    private String startTime;
    private String endTime;

    /**
     * Constructor for a new event task object.
     *
     * @param startTime   starting time of event
     * @param endTime     ending time of event
     * @param description task description of event
     */
    public Event(String startTime, String endTime, String description) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of a event task with its status.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    /**
     * Returns the string representation of the event task to be stored.
     *
     * @return String representing event task to be stored.
     */
    @Override
    public String toStore() {
        return "E | " + super.toStore() + " | " + this.startTime + " | " + this.endTime;
    }

}
