package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an Duke.Tasks.Event.
 * Tasks that start at a specific date/time and
 * ends at a specific date/time.
 *
 * @author Rayson
 */
public class Event extends Task {

    private final LocalDateTime start;
    private final LocalDateTime deadline;

    public Event(String description, LocalDateTime start, LocalDateTime deadline) {
        super(description);
        this.start = start;
        this.deadline = deadline;
    }

    public Event(String description, LocalDateTime start, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)", super.toString(),
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    @Override
    public String formatForStorage() {
        return String.format("E | %s | %s--%s", super.formatForStorage(), this.start, this.deadline);
    }

    @Override
    public boolean isWithinDateRange(LocalDateTime date) {
        return this.start.isBefore(date) && this.deadline.isAfter(date);
    }
}
