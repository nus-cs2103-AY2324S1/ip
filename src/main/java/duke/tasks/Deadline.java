package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.exceptions.DukeException;

/**
 * The Deadline class, which is a Task that has a specified due date.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Instantiates a new Deadline.
     *
     * @param name the description of the Deadline
     * @param by   the due date
     * @throws DukeException the duke exception for when the description is empty or the format is not followed
     */
    public Deadline(String name, String by) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Follow the format for a deadline.");
        }
    }

    /**
     * Returns the String description of a task suitable for file storage.
     *
     * @return the task String
     */
    public String dataString() {
        if (this.isdone()) {
            return "T : 1 : " + this.getname() + ":" + this.by;
        } else {
            return "T : 0 : " + this.getname() + ":" + this.by;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[D]" + super.toString() + " (by: " + this.by.format(format) + ")";
    }
}
