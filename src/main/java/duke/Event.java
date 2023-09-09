package duke;

/**
 * Event is a type of Task that has both a start and end date, in addition to their name and status.
 */
public class Event extends Task {
    //Start timing for the event task.
    protected String startTime;

    //End timing for the event task.
    protected String endTime;

    /**
     * Instantiates an instance of the Event task class.
     *
     * @param classname name of the event.
     * @param startTime the starting time of the event task.
     * @param endTime the ending time of the event task.
     */
    public Event(String classname, String startTime, String endTime) {
        super(classname);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Shows the name of the Event task and its status.
     *
     * @return Name of the Event task and its current status.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + this.startTime + " to: " + this.endTime + ")";
    }
}
