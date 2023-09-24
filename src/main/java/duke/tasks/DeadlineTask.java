package duke.tasks;

import java.time.LocalDateTime;

import duke.util.TimeUtil;

/**
 * Represents a task with a deadline in the Duke application.
 */
public class DeadlineTask extends Task {
    private LocalDateTime endDate;

    /**
     * Constructs a DeadlineTask.
     *
     * @param taskName The name of the task.
     * @param endDate  The deadline of the task.
     */
    public DeadlineTask(String taskName, LocalDateTime endDate) {
        super(taskName, TaskType.DEADLINE);
        this.endDate = endDate;
    }

    /**
     * Retrieves the end date of the task.
     *
     * @return The end date of the task.
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string detailing the task.
     */
    @Override
    public String toString() {
        String formattedEndDate = TimeUtil.formatLocalDateTime(endDate);
        return String.format("%s (by: %s)", super.toString(), formattedEndDate);
    }
}
