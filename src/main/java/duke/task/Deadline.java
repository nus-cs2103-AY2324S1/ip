package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a type of Task with a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate deadline;

    /**
     * The constructor for a Deadline.
     *
     * @param description The description of the task.
     * @param deadline The due date of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of a Deadline.
     *
     * @return Returns the string representation of a Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline.format(FORMAT));
    }
}
