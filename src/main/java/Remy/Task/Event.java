package Remy.Task;

public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Returns an instance of Event with the given description, startTime and endTime.
     * startTime and endTime need not adhere to any time format.
     * @param description The description of the Event.
     * @param startTime The start time of the Event.
     * @param endTime The end time of the Event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns String representation of the Event, labelled [E].
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.startTime + " to " + this.endTime + ")";
    }
}
