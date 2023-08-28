package Tasks;

public class Event extends Task {

    /** Date where the event start. */
    private String from;
    /** Date where the event ends. */
    private String to;

    /**
     * Constructor for Tasks.Event.
     * @param taskName Name of event.
     * @param from Date where event starts.
     * @param to Date where event ends.
     */
    public Event(String taskName, int isDone, String from, String to) {
        super(taskName, isDone);
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

    @Override
    public String toString() {
        return super.toString().replace("/TASK", "event ") + "/from " + from + " /to " + to;
    }
}
