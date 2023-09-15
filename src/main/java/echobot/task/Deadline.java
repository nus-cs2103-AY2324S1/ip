package echobot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Constructs a deadline task with description and due date.
     *
     * @param description Description of the deadline.
     * @param dueDate Due date of the deadline.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Gets the due date of the deadline.
     *
     * @return Due date.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String display() {
        return "[D] " + super.display() + " (by: "
                + dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
