public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String eventDesc, String startTime, String endTime) {
        super(eventDesc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "from: " + this.startTime +
                "to: " + this.endTime;
    }
}
