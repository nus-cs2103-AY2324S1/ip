package duke.task;

/**
 * This class encapsulates an Event task.
 */
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor method for an Event.
     * @param taskDescription Description of the task
     * @param start Starting date and time of the Event
     * @param end Ending date and time of the Event
     */
    public Event(String taskDescription, String start, String end) {
        super(taskDescription);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        assert (start != null) : "The start date and time of an Event task cannot be null.";
        assert (end != null) : "The end date and time of an Event task cannot be null.";

        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
