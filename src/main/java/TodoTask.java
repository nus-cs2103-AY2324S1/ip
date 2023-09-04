import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class TodoTask extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    public TodoTask(String description, LocalDate fromDate, LocalDate toDate, boolean isDone) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + getDescriptionDetails();
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s (from: %s to: %s)", getTaskType(), isDone ? 1 : 0, description,
                fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    private String getDescriptionDetails() {
        return description + " (from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}