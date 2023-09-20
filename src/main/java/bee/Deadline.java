package bee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline, including a date and time.
 * Extends the base class Task and adds functionality for handling deadlines.
 */
public class Deadline extends Task {
    private LocalDateTime localDateTime;

    /**
     * Constructor for creating a new Deadline task.
     *
     * @param description   Description of the task.
     * @param localDateTime Deadline date and time.
     */
    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        assert description != null : "Description cannot be null."; // Assumption: Description should not be null
        assert localDateTime != null : "LocalDateTime cannot be null."; // Assumption: LocalDateTime should not be null
        this.localDateTime = localDateTime;
    }

    /**
     * Constructor for creating a new Deadline task with an additional flag for task completion status.
     *
     * @param description   Description of the task.
     * @param localDateTime Deadline date and time.
     * @param isDone        Completion status of the task.
     */
    public Deadline(String description, LocalDateTime localDateTime, Boolean isDone) {
        super(description, isDone);
        assert description != null : "Description cannot be null."; // Assumption: Description should not be null
        assert localDateTime != null : "LocalDateTime cannot be null."; // Assumption: LocalDateTime should not be null
        this.localDateTime = localDateTime;
    }

    /**
     * Converts the Deadline task to a formatted string representation.
     *
     * @return Formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
