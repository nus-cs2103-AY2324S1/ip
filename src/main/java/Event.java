public class Event extends Task {

    private final String startTime;
    private final String endTime;

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public Event(String eventDesc, boolean isDone, String startTime, String endTime) {
        super(eventDesc, isDone);
        this.taskType = TaskType.EVENT;
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
