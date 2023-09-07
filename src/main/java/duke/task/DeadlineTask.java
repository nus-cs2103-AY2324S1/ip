package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate byDate;

    public DeadlineTask(String description, LocalDate byDate, boolean isDone) {
        super(description, isDone);
        this.byDate = byDate;
    }

    public String toString() {
        String var10000 = super.toString();
        return "[D]" + var10000 + " " + this.getDescriptionDetails();
    }

    public String toFileString() {
        return String.format("%s | %d | %s (by: %s)", this.getTaskType(), this.isDone ? 1 : 0, this.description, this.byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public String getTaskType() {
        return "D";
    }

    private String getDescriptionDetails() {
        String var10000 = this.description;
        return var10000 + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
