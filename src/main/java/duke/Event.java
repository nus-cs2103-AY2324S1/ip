package duke;
/**
 * Event class that is a Task with fields startTime, endTime and a symbol
 */
public class Event extends Task {
    private static final String SYMBOL = "[E]";
    private String startTime;
    private String endTime;

    /**
     * Constructor for Event task
     * @param name name of the Event task
     * @param startTime start time of the event
     * @param endTime end time of the event
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * To get details of start time & end time of the event
     * @return String that contains the start and end time in a specific format
     */
    public String getDetails() {
        return "(from: " + this.startTime + " to: " + this.endTime + ")";
    }
    @Override
    public String toString() {
        return Event.SYMBOL + this.getCheckbox() + this.getName() + " " + this.getDetails();
    }
    @Override
    public String newFormat() {
        return Event.SYMBOL + " | " + this.getInt() + " | " + this.getName() + " | " + this.getDetails();
    }
}
