package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    // Constants
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Fields
    LocalDate deadline;

    // Constructor for duke.task.Deadline
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline.format(FORMAT));
    }
}
