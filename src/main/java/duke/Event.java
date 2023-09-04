package duke;

/**
 * one of the Tasks that user's can add into their list
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * constructor to initialise an Event object
     * @param description the Task description that is obtained from the Task class
     * @param from the from part of the Event time component
     * @param to the by part of the Event time component
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "| " + from + "-" + to;
    }
}
