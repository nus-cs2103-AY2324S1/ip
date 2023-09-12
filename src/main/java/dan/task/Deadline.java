package dan.task;

import dan.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    // fields
    protected LocalDate deadline;

    // toString


    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ ")";
    }

    @Override
    public String saveToString() {
        return "deadline," + super.saveToString() + "," + deadline;
    }

    // Constructor
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }
    public Deadline(String description, int mark, String deadline) {
        super(description, mark != 0);
        this.deadline = LocalDate.parse(deadline);
    }
}
