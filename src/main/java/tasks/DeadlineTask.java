package tasks;

import tasks.Task;
import util.TimeUtil;

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
        String formattedEndDate = TimeUtil.formatLocalDateTime(endDate);
        return String.format("%s (by: %s)", super.toString(), formattedEndDate);
    }
}
