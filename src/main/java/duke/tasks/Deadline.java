package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * A <code>Deadline</code> object corresponds to a deadline task that has a description and a due date.
 */
public class Deadline extends Task {
    private final LocalDate dueDate;

    /**
     * Constructs a <code>Deadline</code> object.
     * @param description The description of the deadline task.
     * @param dueDate The due date of the deadline task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = LocalDate.parse(dueDate, formatter);
    }

    /**
     * Converts a deadline task to file format.
     * @return The due date of the deadline task.
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + dueDate;
    }

    /**
     * Converts a deadline task from file format.
     * @return The due date of the deadline task.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        Deadline deadline = new Deadline(parts[2], parts[3]);
        if (parts[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }

    /**
     * Returns the string representation of a deadline task.
     * @return The string representation of a deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D][" + (getIsDone() ? "X" : " ") + "] " + getDescription() + " (by: " + dueDate.format(formatter) +
                ")";
    }
}

