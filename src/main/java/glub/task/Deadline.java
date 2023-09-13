package glub.task;

import java.time.LocalDateTime;

/**
 * Abstraction of a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for deadline.
     * @param task Task description.
     * @param isDone Task status.
     * @param deadline Date and time of deadline.
     */
    public Deadline(String task, boolean isDone, LocalDateTime deadline) {
        super(task, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toSaveFormat() {
        return "D|" + super.toSaveFormat() + String.format("|%s\n", super.getSaveDateTime(deadline));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", super.getDisplayDateTime(deadline));
    }
}
