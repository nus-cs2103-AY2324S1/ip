package duke.task;

import duke.Storage;

import java.time.LocalDateTime;

/**
 * Event represents a task with a start and end date.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = Storage.stringToDate(startTime);
        this.endTime = Storage.stringToDate(endTime);
    }

    @Override
    public String toDatabaseRepresentation() {
        return String.format("E | %s | %s | %s | %s",
                isDone() ? 1 : 0, getTaskName(), Storage.dateToDatabaseRepresentation(startTime),
                Storage.dateToDatabaseRepresentation(endTime));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), Storage.dateToString(startTime), Storage.dateToString(endTime));
    }
}
