package arona.task;

/**
 * Represents an event task. An event task has a description, a start time, and an end time.
 */
public class EventTask extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new event task with the given description, start time, and end time, marked as undone by default.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new event task with the given description, start time, end time, and marked status.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isMarked    The marked status (1 for marked, 0 for unmarked).
     */
    public EventTask(String description, String from, String to, int isMarked) {
        super(description, isMarked);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the event task, including its status icon, description, and time frame.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from + " " + to + ")";
    }
}
