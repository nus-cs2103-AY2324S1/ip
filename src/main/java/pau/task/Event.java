package pau.task;

/**
 * Represents an Event task that has a description of the task, a start and end date.
 */
public class Event extends Task {

    /**
     * Start date of an event.
     */
    protected String start;

    /**
     * End date of an event.
     */
    protected String end;

    /**
     * Constructs an event.
     *
     * @param description Description of the event.
     * @param start Start date of the event.
     * @param end End date of an event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String writeToFile() {
        String delimiter = " | ";
        String status = this.isDone ? "1" : "0";
        return "E" + delimiter + status + delimiter + this.description + delimiter + this.start + delimiter + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + start + "to:" + end + ")";
    }
}
