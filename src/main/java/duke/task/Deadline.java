package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Deadline extends Task {

    protected LocalDate by;

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
