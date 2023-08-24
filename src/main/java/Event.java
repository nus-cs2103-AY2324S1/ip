public class Event extends Task {
    protected String startDatetime;
    protected String endDatetime;

    public Event(String description, String startDatetime, String endDatetime) {
        super(description);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startDatetime, this.endDatetime);
    }
}
