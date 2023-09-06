package qi.task;

public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String checkBox = this.done ? "[E][X] " : "[E][ ] ";
        String description = String.format("%s (from: %s to: %s)", this.task, this.startTime, this.endTime);
        return checkBox + description;
    }
}
