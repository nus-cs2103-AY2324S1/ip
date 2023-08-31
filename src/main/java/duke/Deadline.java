package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime due;

    public Deadline(String description, LocalDateTime due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    @Override
    public String writeToFile() {
        int mark;
        if (super.getStatusIcon() == "X") {
            mark = 1;
        } else {
            mark = 0;
        }
        return "D | " + mark + " | " + super.writeToFile() + " | " + this.due.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
