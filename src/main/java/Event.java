public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + from + " | " + to + "";
    }
}
