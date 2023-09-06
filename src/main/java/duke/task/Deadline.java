package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnknownCommandException;

/**
 * The Deadline class extends Task and has an additional field
 * to store when the task must be completed by
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Deadline extends Task {

    protected LocalDate by;

    /** Constructor for Deadline */
    public Deadline(String done, String description, LocalDate by) {
        super(description, done);
        this.by = by;
    }

    /**
     * Sets the description and by date.
     *
     * @param description the description of the task.
     * @param by date to finish the task by.
     */
    public Deadline(String commands) throws DukeException {
        String[] items = commands.split(" /");
        if (items.length == 1) {
            throw new EmptyDescriptionException();
        } else if (!items[1].startsWith("by ")) {
            throw new UnknownCommandException();
        } else {
            if (items[1].length() == 3) {
                throw new EmptyDescriptionException();
            } else {
                LocalDate date = LocalDate.parse(items[1].substring(3));
                this.description = items[0];
                this.by = date;
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String showFileRepresentation() {
        return ("D" + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + by + "\n");
    }
}

