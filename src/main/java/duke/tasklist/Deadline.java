package duke.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private final LocalDate date;
    Deadline(String name, LocalDate time) {
        super(name);
        date = time;
    }

    @Override
    public String getText() {
        return super.getText() + " | ";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
