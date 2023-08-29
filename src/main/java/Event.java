public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description, "E");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return super.toString() + " (from: " + startTime + " to " + endTime + ")";
    }

    public String toFileString() {
        return super.toFileString() + " | " + startTime + " - " + endTime;
    }
}
