/**
 * A task which holds the date from and to.
 */
public class Event extends Task {

    /**
     * The start time of the event task.
     */
    private String from;

    /**
     * The end time of the event task.
     */
    private String to;

    /**
     * Constructs an Event task
     *
     * @param description The description of the task.
     * @param from        The start time of the task.
     * @param to          The end time of the task.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.TaskType = TaskType.EVENT;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return this.isDone ? "[E][X] " + this.description + " (from: " + from + " to: " + to + ")"
                : "[E][ ] " + this.description + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Used for easier readability during
     * storing into the file.
     *
     * @return the raw version of the task for storing.
     */
    @Override
    public String getRaw() {
        return "E" + "|" + isDone + "|" + this.description + "|" + from + "|" + to;
    }
}
