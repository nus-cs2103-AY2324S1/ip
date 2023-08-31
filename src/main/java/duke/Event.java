package duke;

public class Event extends Task {
    private String from;
    private String to;

    // Constructor for duke.Event
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    // Constructor for duke.Event with done status
    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    // Get string representation of the duke.Event
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    // Gets string representation of the duke.Todo for hard disk
    public String toStringStorage() {
        String nameField = this.getName();
        String isDoneField = this.isDone() ? "1" : "0";
        String fromField = this.from;
        String toField = this.to;
        return "E|" + isDoneField + "|" + nameField + "|" + fromField + "|" + toField;
    }

    // Gets the start of the duke.Event
    public String getFrom() {
        return this.from;
    }

    // Gets the end of the duke.Event
    public String getTo() {
        return this.to;
    }
}