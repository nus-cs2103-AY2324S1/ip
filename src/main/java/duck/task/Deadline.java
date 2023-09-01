package duck.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(3) + " (by: " + this.getBy() + ")";
    }

    public String type() {
        return "D";
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

}

