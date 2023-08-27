public class Event extends Task{
    /**
     * The start time of the event.
     */
    protected String from;
    /**
     * The end time of the event.
     */
    protected String to;
    /**
     * The type of the task.
     */
    /**
     * Constructs an Event object.
     *
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */

    public Event (String description, String from, String to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        return this.type + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
