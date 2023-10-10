package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Constructor for a deadline task.
     * @param description The description of the task
     * @param dueDate The due date of the task
     * @throws DateTimeParseException if dueDate is not in the correct format
     */
    public Deadline(String description, String dueDate) throws DateTimeParseException {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
    }

    @Override
    public String toLogString() {
        return String.format("D|%s|%s|%s", (isDone ? "X" : "O"), description, dueDate);
    }

    @Override
    public String toString() {
        String taskString = String.format(" (by: %s)", dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return "[D]" + super.toString() + taskString;
    }
}

