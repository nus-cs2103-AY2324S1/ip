package chatty.task;

public class Event extends Task {

    private final String startTime;
    private final String endTime;

    public Event(String taskDescription, String startTime, String endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String status = (this.isDone) ? "[E][X] " : "[E][ ] ";
        String taskString = String.format("%s (from: %s to: %s)", this.task, this.startTime, this.endTime);
        return status + taskString;
    }
}
