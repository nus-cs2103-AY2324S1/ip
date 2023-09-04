import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DeadlineTask extends Task {
    private LocalDate byDate;

    public DeadlineTask(String description, LocalDate byDate, boolean isDone) {
        super(description, isDone);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + getDescriptionDetails();
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s (by: %s)", getTaskType(), isDone ? 1 : 0, description,
                byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    private String getDescriptionDetails() {
        return description + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
