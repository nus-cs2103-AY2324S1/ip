/**
 *
 */
public class Event extends Task{
    protected String startTime;
    protected String endTime;

    /**
     * Constructs a new Event object
     * @param name Name of event
     * @param startTime Start time of event
     * @param endTime End time of event
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * String representation of Event
     * @return String representation of Event
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }

    @Override
    public String generateSaveString() {
        return String.format("E | %b | %s  /from %s /to %s", isDone, name, startTime, endTime);
    }
}
