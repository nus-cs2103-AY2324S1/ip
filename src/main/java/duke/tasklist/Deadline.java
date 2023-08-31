package duke.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * This class extends the base Task class and includes a specific deadline.
 */
class Deadline extends Task {
    private final LocalDate date;

    /**
     * Constructs a Deadline task with the given name and deadline.
     *
     * @param name The name or description of the task.
     * @param time The deadline of the task.
     */
    Deadline(String name, LocalDate time) {
        super(name);
        date = time;
    }

    /**
     * Returns the text representation of the Deadline task.
     * This includes the name and the deadline.
     *
     * @return The text representation of the Deadline task.
     */
    @Override
    public String getText() {
        return super.getText() + " | " + date;
    }

    /**
     * Returns the formatted string representation of the Deadline task.
     * This includes the task type, name, and formatted deadline.
     *
     * @return The formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
