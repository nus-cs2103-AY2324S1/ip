public class Event extends Task {
    private final String eventStartTime;
    private final String eventEndTime;

    public Event(String description, String eventStartTime, String eventEndTime) {
        super(description);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.eventStartTime, this.eventEndTime);
    }
}
