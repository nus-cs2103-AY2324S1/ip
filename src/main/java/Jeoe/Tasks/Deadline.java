package Jeoe.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    public String getEndDateTimeString() {
        return this.by.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        // return this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                // + "T" + this.by.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String replyString(int currNumOfTask) {
        return super.replyString(currNumOfTask);
    }
}