package buddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
    }

    private String getDateString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("%s | %d | %s | %s",
                getTaskType(),
                isDone ? 1 : 0,
                this.description,
                this.getDateString(by));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString(by) + ")";
    }
}
