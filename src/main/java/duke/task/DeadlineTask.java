package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate byDate;

    public DeadlineTask(String description, LocalDate byDate, boolean isDone) {
        super(description, isDone);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.getDescriptionDetails();
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s (by: %s)", getTaskType(), this.isDone ? 1 : 0, this.description,
                this.byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    private String getDescriptionDetails() {
        return this.description + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}