package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDate atDate;

    public EventTask(String description, LocalDate atDate, boolean isDone) {
        super(description, isDone);
        this.atDate = atDate;
    }

    public String toString() {
        String var10000 = super.toString();
        return "[E]" + var10000 + " " + this.getDescriptionDetails();
    }

    public String toFileString() {
        return String.format("%s | %d | %s (at: %s)", this.getTaskType(), this.isDone ? 1 : 0, this.description, this.atDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public String getTaskType() {
        return "E";
    }

    private String getDescriptionDetails() {
        String var10000 = this.description;
        return var10000 + " (at: " + this.atDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
