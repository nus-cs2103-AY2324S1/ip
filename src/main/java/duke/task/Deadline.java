package duke.task;

import java.time.LocalDate;

public class Deadline extends Task {
    protected LocalDate byDate;

    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        String deadline = "(by: " + this.byDate + ")";
        return "[D]" + status + " " + super.description + " " + deadline;
    }
}
