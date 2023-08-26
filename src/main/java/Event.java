public class Event extends Task {

    protected String start;
    protected String end;
    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    public Event(String details, boolean isCompleted, String start, String end) {
        super(details, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(
                " (from: %s to: %s)", this.start, this.end);
    }
}
