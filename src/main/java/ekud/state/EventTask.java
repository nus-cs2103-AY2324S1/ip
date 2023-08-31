package ekud.state;

import ekud.util.DateTime;

/**
 * Represents a task that starts and ends at a certain date/time.
 */
public final class EventTask extends Task {
    private final DateTime from;
    private final DateTime to;

    /**
     * Creates a new task that starts and ends at a certain date/time.
     * 
     * @param title  The title of the task.
     * @param isDone Whether the task is completed.
     * @param from   The start date/time.
     * @param to     The end date/time.
     */
    public EventTask(String title, boolean isDone, DateTime from, DateTime to) {
        super(title, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns when this task starts.
     * 
     * @return The start date/time.
     */
    public DateTime getFrom() {
        return from;
    }

    /**
     * Returns when this task ends.
     * 
     * @return The end date/time.
     */
    public DateTime getTo() {
        return to;
    }

    /**
     * Returns the string representation of this task, to be displayed to the user.
     * 
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
