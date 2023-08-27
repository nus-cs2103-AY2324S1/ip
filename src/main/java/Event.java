public class Event extends Task {
    protected String from;
    protected String by;

    public Event(String description, String from, String by) {
        super(TaskType.EVENT, description);
        this.from = from;
        this.by = by;
    }

    @Override
    public String getString() {
        return "[E]" + super.getString() + " (from: " + this.from + " to: " + this.by + ")";
    }

    @Override
    public String getFileString() {
        return "E|" + super.getFileString() + "|" + this.from + "|" + this.by;
    }
}
