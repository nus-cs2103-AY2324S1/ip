package geraldbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the task list.
 */
public class Deadline extends Task {

    protected LocalDateTime endTime;

    /**
     * Constructs a `Deadline` task with the specified description, completion status, and deadline.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param endTime          The deadline of the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime endTime) {
        super(description, isDone);
        this.endTime = endTime;
    }

    /**
     * Returns the formatted file representation of the `Deadline` task.
     *
     * @return The file format representation of the task.
     */
    @Override
    public String fileFormat() {
        return "D " + super.fileFormat() + " | " + this.endTime;
    }

    /**
     * Returns the string representation of the `Deadline` task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        String formattedBy = endTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
