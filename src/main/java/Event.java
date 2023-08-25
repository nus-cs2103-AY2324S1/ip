/**
 * Represents an event task that occurs within a specified time frame.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs an event task with the given name, start time, and end time.
     *
     * @param name      The name of the event.
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = DateFormatter.format(startTime, "MMM d yyyy");
        this.endTime = DateFormatter.format(endTime, "MMM d yyyy");
    }

    /**
     * Generates a formatted string representation of the event.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }
}
