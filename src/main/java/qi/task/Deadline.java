package qi.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the task categorized as deadline
 * by users.
 */
public class Deadline extends Task {
    private final LocalDate deadline;

    /**
     * Takes in the task description as well as
     * its deadline.
     * @param task String representation of the task.
     * @param deadline LocalDate representing the deadline of the task.
     */
    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String checkBox = this.isDone ? "[D][X] " : "[D][ ] ";
        String description = String.format("%s (by: %s)", this.task,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        return checkBox + description;
    }
}
