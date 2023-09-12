package duke;

/**
 * One of the Tasks that user's can add into their list
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor to initialise an Event object
     * @param description The Task description
     * @param from The 'from' time component
     * @param to The 'by' time component
     */
    private Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public static Event newEvent(String description, String from, String to) {
        return new Event(description, from, to);
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
