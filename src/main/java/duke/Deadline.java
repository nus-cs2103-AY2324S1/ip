package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the task list.
 * A deadline task has a description and a specific deadline date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime date;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param date        The deadline date and time in the format "yyyy-MM-dd HHmm".
     * @throws DukeException If the description or date is empty.
     */
    public Deadline(String description, String date) throws DukeException {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.date = LocalDateTime.parse(date, formatter);

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of a deadline task cannot be empty.");
        }
        if (date.trim().isEmpty()) {
            throw new DukeException("The deadline cannot be empty.");
        }
    }

    /**
     * Returns a string representation of the Deadline task for display.
     *
     * @return A string representing the Deadline task, including its type, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}