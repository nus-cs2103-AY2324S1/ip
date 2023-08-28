package duke.task;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String name, String from, String to) throws DukeException {
        super(name);
        try {
            this.from = LocalDateTime.parse(from, Duke.DATETIME_INPUT_FORMAT);
            this.to = LocalDateTime.parse(to, Duke.DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.");
        }
    }

    public Event(String name, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toDataString() {
        return "E|" + super.toDataString() + "|" + from + "|" + to;
    }
}
