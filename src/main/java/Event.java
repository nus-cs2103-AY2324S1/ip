public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTaskType() {
        return "[E]";
    }

    @Override
    public String getTaskTime() {
        return " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
