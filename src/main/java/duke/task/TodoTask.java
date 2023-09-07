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

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + this.getDescriptionDetails();
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s (from: %s to: %s)", getTaskType(), this.isDone ? 1 : 0, this.description,
                this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    private String getDescriptionDetails() {
        return this.description + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}