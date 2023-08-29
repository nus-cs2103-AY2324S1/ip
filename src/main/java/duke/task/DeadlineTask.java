package duke.task;

import duke.enums.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDate by;


    /**
     * Constructor for duke.task.DeadlineTask.
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
     * Get the deadline of the task.
     *
     * @return deadline of the task.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern(Task.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getBy() + ")";
    }
}
