import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime endDate;
    public DeadlineTask(String taskName, LocalDateTime endDate) {
        super(taskName, TaskType.DEADLINE);
        this.endDate = endDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), endDate);
    }
}
