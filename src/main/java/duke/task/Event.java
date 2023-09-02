package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific event in the Duke application.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a Event object with the specified description and start and end date/time.
     *
     * @param description The description of the event task.
     * @param start       The start date and time.
     * @param end         The end date and time.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return A formatted string displaying the event task's details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }

    /**
     * Generates a formatted string representation of the event task for saving.
     *
     * @return A formatted string suitable for saving in a data file.
     */
    @Override
    public String toSaveLine() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description,
                             start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                             end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
