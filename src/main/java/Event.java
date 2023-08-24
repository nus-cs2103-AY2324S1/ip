public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[E][X] " + this.task + " (from: " + startTime + " to: " + endTime + ")";
        }
        return "[E][ ] " + this.task + " (from: " + startTime + " to: " + endTime + ")";
    }
}
