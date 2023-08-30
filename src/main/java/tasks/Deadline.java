package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String status, String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
        if (status.equals("1")) {
            this.isDone = true;
        }
    }

    @Override
    public String toString() {
        String strBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + strBy + ")";
    }

    @Override
    public String toStringForFile() {
        return "D | " + super.toStringForFile() + " | " + this.by;
    }
}
