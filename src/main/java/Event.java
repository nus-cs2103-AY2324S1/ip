public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the starting time of the event.
     * 
     * @return The starting time of the event.
     */
    public String getStartTime() {
        return this.from;
    }

    /**
     * Returns the ending time of the event.
     * 
     * @return The ending time of the event.
     */
    public String getEndTime() {
        return this.to;
    }

    /**
     * Returns a string representation of Event.
     *
     * @return A string representation of Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
