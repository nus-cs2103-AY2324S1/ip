package duke.tasks;

import duke.util.TimeUtil;

import java.time.LocalDateTime;

/**
 * Represents an event task in the Duke application.
 */
public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs an EventTask.
     *
     * @param taskName  The name of the task.
     * @param startDate The start date of the event.
     * @param endDate   The end date of the event.
     */
    public EventTask(String taskName, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskName, TaskType.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string detailing the task.
     */
    @Override
    public String toString() {
        String formattedStartDate = TimeUtil.formatLocalDateTime(startDate);
        String formattedEndDate = TimeUtil.formatLocalDateTime(endDate);
        return String.format("%s (from: %s to: %s)", super.toString(), formattedStartDate, formattedEndDate);
    }
}