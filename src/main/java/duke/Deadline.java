package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D] " + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")");
    }
}