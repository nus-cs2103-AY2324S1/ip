package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.enums.TaskType;

/**
 * Represents a DeadlineTask class that deals with the deadline task that has a deadline `by` attached to it.
 */
public class DeadlineTask extends Task {
    private final LocalDate by;

    /**
     * Constructor for DeadlineTask.
     *
     * @param description of the task.
     * @param by          deadline of the task.
     */
    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getFileDescriptor() {
        return super.getFileDescriptor() + String.format("| %s | %s", this.by.toString(), TaskType.DEADLINE);
    }

    /**
     * Returns the deadline of the task.
     *
     * @return deadline of the task in the format of MMM dd yyyy.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern(Task.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getBy() + ")";
    }
}
