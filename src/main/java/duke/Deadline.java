package duke;

import java.time.*;
import java.time.format.*;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline object with the specified description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Changes the format of a LocalDate to "dd LLLL yyyy".
     *
     * @param date The LocalDate to be formatted.
     * @return The formatted date as a string.
     */
    public String changeFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String dateFormatted = date.format(formatter);
        return date.format(formatter);
    }

    /**
     * Gets the deadline date of the task.
     *
     * @return The deadline date.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation including task type, description, and deadline date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + changeFormat(by) + ")";
    }
}
