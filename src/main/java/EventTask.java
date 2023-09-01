import java.time.LocalDateTime;

public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    public EventTask(String taskName, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskName, TaskType.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), startDate, endDate);
    }
}
