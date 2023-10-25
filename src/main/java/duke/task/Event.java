package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific Event in the Duke application.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a Event object with the specified description, start and end time.
     *
     * @param description The description of the task.
     * @param from The start date and time of the task.
     * @param to The end date and time of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task for display purposes.
     *
     * @return A formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task for writing to a file.
     * The date and time are formatted differently for file storage.
     *
     * @return A formatted string representation of the Event task for file storage.
     */
    @Override
    public String writeFileString() {
        String toFormat = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String fromFormat = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "[E]" + super.toString() + " (from: " + fromFormat
                + " to: " + toFormat + ")";
    }
}
