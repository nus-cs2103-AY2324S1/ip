package duke.task;

import duke.Duke;
import duke.exception.DukeException;


import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String name, String by) throws DukeException {
        super(name);
        try {
            this.by = LocalDateTime.parse(by, Duke.DATETIME_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.");
        }
    }

    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return "D|" + super.toDataString() + "|" + by;
    }
}
