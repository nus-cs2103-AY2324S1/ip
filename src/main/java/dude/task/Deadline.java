package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline that extends a task.
 * A <code>Deadline</code> object has a <code>String</code> description,
 * and <code>LocalDateTime</code> due time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new Deadline object with the specified description and due time.
     *
     * @param description A short description of the event.
     * @param by The time when the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "deadline";
    }

    @Override
    public String saveTask() {
        String data = "D " + super.saveTask() + " | " + this.by + "\n";
        return data;
    }

    /**
     * Returns a formatted string representation of the deadline.
     *
     * @return A formatted string describing the deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a", Locale.ENGLISH);
        String formattedDateTime = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }
}
