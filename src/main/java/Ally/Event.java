package Ally;

public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Ally.Event.
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String formatFile() {
        return "E" + " | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from + " | " + to;
    }


    /**
     * Overrides the toString() method in Ally.Task.
     * @return the string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
