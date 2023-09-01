package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s %s)", getStatusIcon(), description,
                (byDate != null ? byDate : ""), (byTime != null ? byTime : ""));
    }

    @Override
    public String toFileString() {
        return String.format("D # %d # %s # %s %s", (isDone ? 1 : 0), description,
                (byDate != null ? byDate : ""), (byTime != null ? byTime : ""));
    }
}