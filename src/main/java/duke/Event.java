package duke;

public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for Event.
     *
     * @param name Description of the Event.
     * @param from Starting time of the Event.
     * @param to Ending time of the Event.
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event if a done status is supplied.
     *
     * @param name Description of the Event.
     * @param from Starting time of the Event.
     * @param to Ending time of the Event.
     * @param isDone Boolean representing whether the Event is done.
     */
    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the String representation of the Event.
     *
     * @return String representation of the Event.
     */
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Gets the String representation of the Event in the storage.
     *
     * @return String representation of the Event in the storage.
     */
    public String toStringStorage() {
        String nameField = this.getName();
        String isDoneField = this.isDone() ? "1" : "0";
        String fromField = this.from;
        String toField = this.to;
        return "E|" + isDoneField + "|" + nameField + "|" + fromField + "|" + toField;
    }
}