package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * The Deadline class represents a task with a specific deadline.
 * It is a subclass of the Task class.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and deadline date.
     *
     * @param description The description of the task.
     * @param by The deadline date in string format (e.g., "yyyy-MM-dd").
     * @throws DukeException If there is an issue while parsing the date.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = parseDate(by);
    }

    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "D | " + done + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
