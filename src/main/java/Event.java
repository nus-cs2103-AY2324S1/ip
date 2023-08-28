import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = this.convertToDateTime(startTime);
        this.endTime = this.convertToDateTime(endTime);
    }

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String displayStartTime() {
        return this.displayTime(startTime);
    }

    public String saveStartTime() {
        return this.saveTime(this.startTime);
    }

    public String displayEndTime() {
        return this.displayTime(this.endTime);
    }

    public String saveEndTime() {
        return this.saveTime(this.endTime);
    }

    @Override
    public String getOutputString() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, 
        this.saveStartTime().concat(" to " + this.saveEndTime()));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.displayStartTime() + " to: " 
        + this.displayEndTime() + ")";
    }
}
