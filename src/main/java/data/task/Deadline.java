package data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        setBy(by);
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public String toSaveDataFormat() {
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, getDescription(),
                getBy().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                getBy().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")));
    }
}