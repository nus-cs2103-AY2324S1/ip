package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please input in the form of yyyy-MM-dd");
        }
    }
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}

