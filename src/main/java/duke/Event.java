package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String name, boolean isMarked, LocalDateTime startTime, LocalDateTime endTime) {
        super(name, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String status = isMarked ? "[X]" : "[ ]";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String startTimeStr = startTime.format(formatter);
        String endTimeStr = endTime.format(formatter);
        return "[E]" + status + " " + name + " (from: " + startTimeStr + " to: " + endTimeStr + ")";
    }
}

