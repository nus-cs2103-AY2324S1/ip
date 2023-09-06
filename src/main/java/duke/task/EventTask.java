package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDate atDate;

    public EventTask(String description, LocalDate atDate, boolean isDone) {
        super(description, isDone);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + getDescriptionDetails();
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s (at: %s)", getTaskType(), isDone ? 1 : 0, description,
                atDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    private String getDescriptionDetails() {
        return description + " (at: " + atDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
