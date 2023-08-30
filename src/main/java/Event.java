package main.java;
/**
 * Represents an event task, which is a task that start at a specific date/time and ends at a specific date/time
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an Event object with the specified description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time for the event.
     * @param to          The end time for the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Constructs an Event object with the specified description, start time, end time and completion status.
     *
     * @param description The description of the event task.
     * @param from        The start time for the event.
     * @param to          The end time for the event.
     * @param bool        The completion status for the event.
     */
    public Event(String description, String from, String to, Boolean bool) {
        super(description, bool);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a string representation of the task's description and completion status for saving.
     *
     * @return A formatted string containing completion status and description.
     */
    @Override
    public String getSaveDescription() {
        String tmp = "E " + super.getSaveDescription() + " | "
                + this.from + " | " + this.to + "\n";
        return tmp;
    }
}