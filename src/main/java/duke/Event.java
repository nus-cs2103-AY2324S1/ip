package duke;

/**
 * Represents an event task with a description and a time duration.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Initializes a new Event task with the given description and time duration.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string representation of the Event task, including its type, description, and time duration.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }

    /**
     * Returns a string representation of the Event task to be saved into a file.
     *
     * @return A string representing the Event task in a file format.
     */
    @Override
    public String getDescription() {
        return "E" + super.getDescription() + " |" + this.from + "-" + this.to;
    }
}
