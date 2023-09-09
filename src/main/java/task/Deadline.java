package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super( "DEADLINE",description);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString(){
        String tempString = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[D] " + super.toString() + " (by: " + tempString +")";
    }

    @Override
    public String toFileString() {
        return TaskType.DEADLINE.toString() + " | " + (isCompleted() ? "1" : "0") + " | " + getDescription() + " | " + getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
