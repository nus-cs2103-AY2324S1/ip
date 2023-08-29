package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        String deadline = "(by: " + this.by + ")";
        return "[D]" + status + " " + super.description + " " + deadline;
    }
}
