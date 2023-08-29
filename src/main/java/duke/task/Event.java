package duke.task;

/**
 * The Event class represents a task that takes place during a specific time frame.
 * It inherits from the Task class and adds start and end time fields.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    private final String from;

    /**
     * The end time of the event.
     */
    private final String to;

    /**
     * Constructs a new Event object with the specified description, completion status, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param isDone      The completion status of the Event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event object with the specified description, start time, and end time.
     * The event is initially marked as not done.
     *
     * @param description The description of the Event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string indicating the task type, completion status, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a formatted string for writing the Event task to a file.
     *
     * @return A formatted string containing task details for file storage.
     */
    @Override
    public String writeFile() {
        return "E | " + super.writeFile() + " | " + this.from + " | " + this.to;
    }
}
