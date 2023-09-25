package duke;

/**
 * Class to handle tasks of type Event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Initialises an Event task.
     *
     * @param description task description.
     * @param from start date and time.
     * @param to end date and time.
     * @param taskType type of task.
     */
    public Event(String description, String from, String to, char taskType) {
        super(description, taskType);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + "-" + to;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
