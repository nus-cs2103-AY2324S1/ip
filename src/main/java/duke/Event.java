package duke;

/**
 * The Event class represents a task with a specified starting and ending date and time. It is a subclass of the Task class.
 */
public class Event extends Task {
    /**
     * The starting date and time of the event in string format.
     */
    protected String from;
    /**
     * The ending date and time of the event in string format.
     */
    protected String to;
    /**
     * A boolean indicating whether the event task is marked as done or not.
     */
    protected boolean isDone = false;

    /**
     * Constructs an Event object with a description, starting date, and ending date.
     *
     * @param description The description of the event.
     * @param from        The starting date and time of the event.
     * @param to          The ending date and time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event object with a description, starting date, ending date, and a boolean indicating whether it is done.
     *
     * @param description The description of the event.
     * @param from        The starting date and time of the event.
     * @param to          The ending date and time of the event.
     * @param isDone      A boolean indicating whether the event task is marked as done.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the event, including its type, description, and date/time details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a string representation of the Event object suitable for saving to a file.
     *
     * @return A string containing the event's details separated by a predefined divider.
     */
    @Override
    public String toSaveString() {
        String divider = " | ";
        return "E" + divider + super.toSaveString() + divider + this.from + divider + this.to;
    }

}