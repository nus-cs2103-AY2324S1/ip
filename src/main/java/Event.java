/**
 * Represents an Event task
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for Event class.
     *
     * @param description Description of event.
     * @param isDone      Whether event is done.
     * @param from        Start time of event.
     * @param to          End time of event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the description, start time, and end time of the Event, to output to the user.
     * 
     * @return A String, properly formatting the status and description of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the isDone, description, start time, and end time of the Event, to input into the tasks.txt file.
     * 
     * @return A String, properly formatting the description of the event task.
     */
    public String toFileString() {
        // Convert task to file format string
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + "-" + to;
    }

}
