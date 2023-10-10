package duke;

/**
 * Class to handle tasks of type Event.
 */
public class Event extends Task {
    private String from;
    private String to;

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

    /**
     * Returns the string representation of the event task to be stored in a file.
     *
     * @return File string representation of the task.
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + from + "-" + to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
