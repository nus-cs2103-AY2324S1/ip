package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline inherits from Task Class.
 * A Deadline object encapsulates the name of task,
 * status of task, and the deadline of task.
 *
 * @author ruo-x
 */
public class Deadline extends Task {
    /** Deadline of task */
    private final LocalDateTime deadline;

    /**
     * Constructor of a Deadline object.
     *
     * @param isDone Status of task.
     * @param taskName Name of task.
     * @param deadline Deadline of task.
     */
    public Deadline(boolean isDone, String taskName, LocalDateTime deadline) {
        super(isDone, taskName);
        this.deadline = deadline;
    }

    /**
     * Formats deadline of task into the desired format.
     *
     * @param deadline Deadline of task as a LocalDateTime object.
     * @return Formatted deadline of task as a String.
     */
    public String getDeadlineString(LocalDateTime deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy 'at' HH:mm");
        return deadline.format(formatter);
    }

    /**
     * Returns a string format of a Deadline object.
     *
     * @return String format of a Deadline object.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[D]" + "[X] " + this.taskName + "(by: " + getDeadlineString(this.deadline) + ")";
        } else {
            return "[D]" + "[ ] " + this.taskName + "(by: " + getDeadlineString(this.deadline) + ")";
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toStoreString() {
        if (this.isDone) {
            return "D/@/1/@/" + this.taskName + "/@/" + this.deadline;
        } else {
            return  "D/@/0/@/" + this.taskName + "/@/" + this.deadline;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toUpdateString(int i) {
        return "D/@/" + i + "/@/" + this.taskName + "/@/" + this.deadline;
    }
}
