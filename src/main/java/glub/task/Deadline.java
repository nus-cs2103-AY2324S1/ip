package glub.task;

import java.time.LocalDateTime;

/**
 * Abstraction of a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for deadline with tag.
     * @param task Task description.
     * @param isDone Task status.
     * @param tag Task tag.
     * @param deadline Date and time of deadline.
     */
    public Deadline(String task, boolean isDone, String tag, LocalDateTime deadline) {
        super(task, isDone, tag);
        this.deadline = deadline;
    }

    @Override
    public String toSaveFormat() {
        return "D|" + super.toSaveFormat() + String.format("|%s\n", super.getSaveDateTime(deadline));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format("(by: %s) %s", super.getDisplayDateTime(deadline), super.getTag());
    }
}
