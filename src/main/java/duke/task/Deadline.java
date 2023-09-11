package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.helper.DateHelper;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Deadline Constructor.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = DateHelper.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.WRONG_DATETIME_FORMAT);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeToFile() {
        int completed = this.isDone ? 1 : 0;
        return "D " + "| " + completed + " | " + this.description + " | " + DateHelper.saveFormat(this.by) + "\r\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateHelper.format(by) + ")";
    }
}
