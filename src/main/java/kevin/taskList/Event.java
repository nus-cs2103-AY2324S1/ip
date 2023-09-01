package kevin.taskList;

/**
 * A class to represent the Event Task.
 */
public class Event extends Task{
    private final String startTime;
    private final String endTime;

    /**
     * Constructor to initialize Event.
     * @param isDone This is a boolean to mark whether the Event is done or not.
     * @param name This is the name description of the event.
     * @param startTime This is a string for the event start time.
     * @param endTime This is a string for the event end time.
     */
    public Event(Boolean isDone, String name, String startTime, String endTime) {
        super(isDone, name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the Event Object to be written to the file.
     */
    public String toText() {
        return "Event - "  + isDone + " - " + name + " -" + startTime + " -" + endTime + System.lineSeparator();
    }

    /**
     * {@inheritDoc}
     * @return Returns a string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.startTime + " to:" + this.endTime + ")";
    }
}
