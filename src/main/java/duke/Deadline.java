package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    private String identifier;

    public Deadline(String taskDescription, LocalDateTime by, boolean isDone) {
        super(taskDescription, isDone);
        this.by = by;
        this.identifier = "[D]";
    }

    public String toString() {
        return this.identifier + super.toString() + "(by: " +
                by.format(DateTimeFormatter.ofPattern("LLL dd yyyy Ka")) + ")";
    }
}
