package sally;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given task description and deadline.
     *
     * @param task The description of the deadline task.
     * @param by   The specific deadline date and time.
     */
    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.by = by;
    }

    /**
     * Constructs a Deadline object with the given task description, deadline, and priority.
     *
     * @param task The description of the deadline task.
     * @param by   The specific deadline date and time.
     * @param priority The priority of the deadline task.
     */
    public Deadline(String task, LocalDateTime by, String priority) {
        super(task, priority);
        this.by = by;
    }

    /**
     * Converts the Deadline object to a formatted string representation.
     *
     * @return A formatted string indicating the deadline task's status, description, and deadline.
     */
    @Override
    public String toString() {
        String formattedDate;
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        formattedDate = by.format(outputFormatter);

        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
