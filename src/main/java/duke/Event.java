package duke;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Returns an Event Task Object
     *
     * @param description Task Description (String)
     * @param from User specifications on when the Event Task begins (String)
     * @param to User specifications on when the Event Task ends (String)
     */
    public Event(String description, String from, String to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
