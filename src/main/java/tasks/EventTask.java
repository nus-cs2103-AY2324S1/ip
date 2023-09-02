package tasks;

import tasks.Task;
import util.TimeUtil;

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
        String formattedStartDate = TimeUtil.formatLocalDateTime(startDate);
        String formattedEndDate = TimeUtil.formatLocalDateTime(endDate);
        return String.format("%s (from: %s to: %s)", super.toString(), formattedStartDate, formattedEndDate);
    }
}
