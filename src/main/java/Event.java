public class Event extends Task {

    /** Date where the event start. */
    private String from;
    /** Date where the event ends. */
    private String to;

    /**
     * Constructor for Event.
     * @param taskName Name of event.
     * @param from Date where event starts.
     * @param to Date where event ends.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns duration of the event.
     * @return Duration of event.
     */
    private String getDuration() {
        return from + " - " + to;
    }

    /**
     * Returns the string representation of the status of the event
     * @return Status of the event
     */
    @Override
    public String getTask() {
        return "Event ->" + super.getTask() + " (" + getDuration() + ")";
    }
}
