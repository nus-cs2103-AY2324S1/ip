package duke.task;

import duke.Storage;

import java.time.LocalDateTime;

/**
 * Deadline represents a task with an end date.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = Storage.stringToDate(deadline);
    }

    @Override
    public String toDatabaseRepresentation() {
        return String.format("D | %s | %s | %s",
                isDone() ? 1 : 0, getTaskName(), Storage.dateToDatabaseRepresentation(this.deadline));
    }

    @Override
    public LocalDateTime getDateTime() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Storage.dateToString(this.deadline));
    }
}
