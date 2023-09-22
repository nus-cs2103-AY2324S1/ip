package tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String by) {
        super(description);
        this.by = stringToDate(by);
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString(by) + ")";
    }
}
