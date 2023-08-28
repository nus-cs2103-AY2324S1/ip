package Eddie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate deadline;

    public Deadline(String name, LocalDate deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getType() {
        return "D";
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatus() + "]" + this.getName() + " (by: " + this.getDeadline() + ")";
    }
}
