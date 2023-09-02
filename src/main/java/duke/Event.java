package duke;

public class Event extends Task {
    protected String by;
    protected String from;

    public Event(String description, String from, String by) {
        super(description);
        this.by = by;
        this.from = from;
    }

    public String getBy() {
        return this.by;
    }
    public String getFrom(){
        return this.from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + by + ")";
    }
}
