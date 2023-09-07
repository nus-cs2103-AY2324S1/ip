package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TodoTask extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    public TodoTask(String description, LocalDate fromDate, LocalDate toDate, boolean isDone) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String toString() {
        String var10000 = super.toString();
        return "[T]" + var10000 + " " + this.getDescriptionDetails();
    }

    public String toFileString() {
        return String.format("%s | %d | %s (from: %s to: %s)", this.getTaskType(), this.isDone ? 1 : 0, this.description, this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public String getTaskType() {
        return "T";
    }

    private String getDescriptionDetails() {
        String var10000 = this.description;
        return var10000 + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
