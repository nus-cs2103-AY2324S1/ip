package duke.task;

import duke.DateHelper;
import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

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
        return "[D]" + super.toString() + "(by: " + DateHelper.format(by) + ")";
    }
}
