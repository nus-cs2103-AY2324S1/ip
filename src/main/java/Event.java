/**
 * Event is a type of Task that has both a start and end date, in addition to their name and status.
 */
public class Event extends Task {
    //Start timing for the event task.
    protected String start;

    //End timing for the event task.
    protected String end;

    /**
     * Instantiates an instance of the Event task class.
     *
     * @param classname name of the event.
     * @param start the starting time of the event task.
     * @param end the ending time of the event task.
     */
    public Event(String classname, String start, String end) {
        super(classname);
        this.start = start;
        this.end = end;
    }

    /**
     * Shows the name of the Event task and its status.
     *
     * @return Name of the Event task and its current status.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From: " + this.start + " to: " + this.end + ")";
    }
}
