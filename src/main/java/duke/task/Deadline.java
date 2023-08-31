package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task.
 * A Deadline task is a task that needs to be done before a specific date and time.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description Description of the task.
     * @param by The deadline of the task in the format "d/M/yyyy HHmm".
     * @throws DukeException If the provided date format is incorrect.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format provided. Please use the format d/M/yyyy HHmm.");
        }
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return The deadline as a LocalDateTime.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Converts the Deadline task to a string representation.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Converts the Deadline task to a string representation suitable for file storage.
     *
     * @return String representation of the Deadline task for file storage.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(formatter);
    }
}
