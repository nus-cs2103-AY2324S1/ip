package koko;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {

    private final LocalDate by;

    /**
     * Constructs a Deadline with the specified name and due date.
     *
     * @param name The name of the deadline.
     * @param by   The due date of the deadline.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Creates a Deadline object from file data.
     * The parts array is expected to contain:
     * - the type of task (should be "D")
     * - whether the task is done ("1" for done, "0" for not done)
     * - the name of the task
     * - the due date in yyyy-mm-dd format
     *
     * @param parts An array of task data read from the file.
     * @return A Deadline object created from the file data.
     * @throws DukeException If the due date is in the wrong format.
     */
    public static Deadline fromFileFormat(String[] parts) throws DukeException {
        boolean isDone = "1".equals(parts[1].trim());
        String name = parts[2].trim();
        LocalDate byDate;
        try {
            byDate = LocalDate.parse(parts[3].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Deadline /by date should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }
        Deadline deadline = new Deadline(name, byDate);
        if (isDone) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Converts this Deadline object to a formatted string suitable for file storage.
     *
     * @return A string in file storage format.
     */
    @Override
    public String toFileFormat() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.name + "|" + this.by + "|";
    }

    /**
     * Returns the string representation of this Deadline object.
     * The string includes the type of task, the done status, the task name, and the due date.
     *
     * @return A string representing the Deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = this.by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
