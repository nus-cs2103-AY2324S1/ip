public class Event extends Task {

    String startTime;
    String endTime;
    public Event(String startTime, String endTime, String description) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

}
