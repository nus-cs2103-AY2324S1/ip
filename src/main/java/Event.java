public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getOutputString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, 
        startTime.concat("-" + endTime));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " 
        + this.endTime + ")";
    }
}
