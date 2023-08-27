public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String taskName, String startTime, String endTime, boolean isDone) {
        super(taskName, isDone);
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

    @Override
    public String toSaveFormat() {
        return "E | " + (super.isDone() ? "1" : "0") + " | " + this.getTaskName() + " | " + this.startTime + "-" + this.endTime;
    }
}
