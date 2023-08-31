package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private DateTimeFormatter formatterSave = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.deadline.format(formatterSave);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline.format(formatter) + ")";
    }
}
