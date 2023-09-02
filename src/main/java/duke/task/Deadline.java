package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadlineDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description, "D");
        this.deadlineDate = deadlineDate;
    }

    public String toString() {
        return super.toString() + " (by: " + deadlineDate.format(formatter) + ")";
    }

    public String toFileString() {
        return super.toFileString() + " | " + deadlineDate;
    }
}