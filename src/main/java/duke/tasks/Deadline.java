package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.exceptions.DukeException;

public class Deadline extends Task {
    private final LocalDate by;
    public Deadline(String name, String by) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a duke.tasks.Deadline cannot be empty.");
        }
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Follow the format for a duke.tasks.Deadline.");
        }
    }

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
