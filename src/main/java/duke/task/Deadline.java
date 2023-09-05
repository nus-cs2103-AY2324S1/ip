package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represent tasks of type deadline.
 *
 * @author Armando Jovan Kusuma
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a deadline task with the specified
     * description and due date.
     *
     * @param description the description of the deadline task.
     * @param by the due date.
     * @throws DukeException If the date format is wrong.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please input in the form of yyyy-MM-dd");
        }
    }

    /**
     * Returns the string representation of the deadline task.
     */
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}

