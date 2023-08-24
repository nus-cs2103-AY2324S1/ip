public class Event extends Task {
    private String eventStart;
    private String eventEnd;
    public Event(String name, boolean isDone, String eventStart, String eventEnd) {
        super(name, isDone);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", eventStart, eventEnd);
    }
}
