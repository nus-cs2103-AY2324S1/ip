public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;
    Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s, to: %s)", startDateTime, endDateTime);
    }
}
