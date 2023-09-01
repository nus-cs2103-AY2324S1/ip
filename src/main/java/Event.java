/**
 * Represents a Event task in Richie application
 */
class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for the Event task
     * @param description Description of the task
     * @param from The time that the event begins
     * @param to The time that the event ends
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

    /**
     * Returns string date and time of when task starts
     * @return String of when the task starts
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns string date and time of when task is done
     * @return String of when the task ends
     */
    public String getTo() {
        return this.to;
    }
}
