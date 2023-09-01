package duke.task;

import duke.DateHelper;
import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = DateHelper.parse(from);
            this.to = DateHelper.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException(DukeException.WRONG_DATETIME_FORMAT);
        }
    }

    @Override
    public String writeToFile() {
        int completed = this.isDone ? 1 : 0;
        return "E " + "| " + completed + " | " + this.description + " | " + DateHelper.saveFormat(this.from)
                + " | " + DateHelper.saveFormat(this.to) + "\r\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateHelper.format(from) + " to: " + DateHelper.format(to) + ")";
    }
}
