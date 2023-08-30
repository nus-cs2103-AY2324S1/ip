package duke.tasks;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    public Deadline(String description, String date) throws DukeException {
        super(description);

        try {
            LocalDateTime d1 = LocalDateTime.parse(date, inputFormatter);
            by = d1.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("duke.tasks.Deadline time must be in this format: yyyy-mm-dd hh:mm");
        }

    }

    public String exportData() {
        return "D | " + this.getStatusIcon() + " | " + this.description + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
