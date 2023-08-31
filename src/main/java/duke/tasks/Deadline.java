package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the chat bot.
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructs a deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a deadline task with the given description, deadline, and done status.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline of the task.
     * @param isDone      The done status of the task.
     */
    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    /**
     * Formats the deadline task for storage in a data file.
     *
     * @return The formatted string for storage.
     */
    @Override
    public String formatForStorage() {
        return String.format("D | %s | %s",
                super.formatForStorage(), deadline);
    }

    /**
     * Checks if the deadline task is within the given date range.
     *
     * @param date The date to check against.
     * @return True if the deadline task is within the date range, false otherwise.
     */
    @Override
    public boolean isWithinDateRange(LocalDateTime date) {
        return deadline.toLocalDate().equals(date.toLocalDate());
    }
}
