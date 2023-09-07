package buddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // this.by = LocalDate.parse(by, formatter);
    }

    private String getDateString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
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
                this.getDateString(by, "yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateString(by, "yyyy-MM-dd") + ")";
    }
}
