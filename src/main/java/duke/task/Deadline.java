package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description, completion status, and a deadline date.
 */
public class Deadline extends Task {
    private LocalDate deadline = null;

    /**
     * Constructs a deadline task with the specified description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.deadline = LocalDate.parse(by, inputFormatter);
    }

    /**
     * Returns a string representation of the deadline task,
     * including its completion status icon, description, and deadline date.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + deadline.format(outputFormatter) + ")";
    }
}
