/**
 * The Event class represents a task that occurs within a specific time frame.
 * It extends the Task class and adds 'from' and 'to' fields to store the event timing.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the given description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides the toString method to format the Event task's details.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
