package bruno.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * The Deadline task is responsible for all tasks of Deadline type.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Creates a new instance of the Deadline class.
     *
     * @param description Description of the task.
     * @param by          Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        this.by = this.convertToLocalDateTime(by);
    }

    @Override public String getString() throws DateTimeException {
        return "[D]" + super.getString() + " (by: " + convertDateTimeToString(by) + ")";
    }

    @Override public String getFileString() {
        return "D|" + super.getFileString() + "|" + by.toString().replace('T', ' ');
    }

    public LocalDateTime getBy() {
        return this.by;
    }
}
