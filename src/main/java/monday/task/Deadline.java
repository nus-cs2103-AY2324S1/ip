package monday.task;

import java.time.LocalDateTime;

import monday.monday.dateTime.DateFormatter;

/**
 * The Deadlines class extends the Task class.
 * It contains a description, a deadline, and inherits the
 * completion status functionality from the Task class.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadlines object with the given description and deadline.
     *
     * @param description the description of the Deadlines task
     * @param deadline the deadline of the Deadlines task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = DateFormatter.parseTime(deadline, "yyyy-MM-dd HH:mm");
    }

    /**
     * Returns a string representation of the Deadlines task,
     * including its task type indicator, description, and deadline.
     *
     * @return a string representation of the Deadlines task
     */
    @Override
    public String toString() {
        String formatDateTime = DateFormatter.format(deadline, "dd-MM-yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + formatDateTime + ")";
    }
}