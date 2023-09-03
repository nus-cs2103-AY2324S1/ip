package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime date;

    public Deadline(String description, String date) throws DukeException {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.date = LocalDateTime.parse(date, formatter);

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of a deadline task cannot be empty.");
        }
        if (date.trim().isEmpty()) {
            throw new DukeException("The deadline cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}