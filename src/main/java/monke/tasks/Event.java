package monke.tasks;

/**
 * The Event class represents an event task in the Monke application.
 * It extends the Task class.
 */
public class Event extends Task {
    /** The start time of the event. */
    private String start;

    /** The end time of the event. */
    private String end;

    /**
     * Constructs an Event task with the specified description, start time and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     * Includes its status icon, description, start time, and end time.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }

    /**
     * Formats the Event task data for saving to a file.
     *
     * @return A formatted string representing the Event task data.
     */
    @Override
    public String formatData() {
        return String.format("E | %d | %s | %s | %s\n", this.isDone ? 1 : 0, this.description, this.start, this.end);
    }
}
