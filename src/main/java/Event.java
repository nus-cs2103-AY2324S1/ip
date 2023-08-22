public class Event extends Task {
    protected String eventStart;
    protected String eventEnd;

    public Event(String description, String eventStart, String eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + eventStart.replace("from", "(from:")
                + eventEnd.replace("to", "to:") + ")";
    }
}
