package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type Deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;
    private final DateTimeFormatter formatter;

    /**
     * A constructor for a task of type Deadline.
     *
     * @param description the task details.
     * @param by the deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
        this.formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        assert this.by != null : "by should not be null";
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the task in file format.
     *
     * @return the string representation in file format.
     */
    @Override
    public String toFileFormat() {
        return "D | " + this.isDone + " | " + this.description + " | " + this.by.format(formatter);
    }

    /**
     * Returns the task type.
     *
     * @return the task type.
     */
    @Override
    public String getTaskType() {
        return "deadline";
    }

    @Override
    public void updateTask(String description) throws DukeException {
        throw new DukeException(ExceptionTypes.INCOMPLETEUPDATEDETAILS);
    }

    /**
     * Updates the task with the new deadline.
     *
     * @param description the new task details.
     * @param by the new deadline.
     */
    @Override
    public void updateTask(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void updateTask(String description, LocalDateTime from, LocalDateTime to) throws DukeException {
        throw new DukeException(ExceptionTypes.INCOMPLETEUPDATEDETAILS);
    }
}

