package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatDateTime(by) + ")";
    }

    @Override
    public String toStorageFormat() {
        return ("D" + super.toStorageFormat() + " | " + by);
    }
}
