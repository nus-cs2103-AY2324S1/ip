public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Constructor for the Event class
     *
     * @param description the name/description of the event
     * @param from the starting date & time of the event
     * @param to the ending date & time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the string representation of this event, including its type of task, completion status,
     * description, and duration (date & time)
     *
     * @return the string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
