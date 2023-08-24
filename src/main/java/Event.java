public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Make an event task
     * @param description event description
     * @param from event start
     * @param to event end
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
