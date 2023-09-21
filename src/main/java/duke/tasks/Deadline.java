package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }
        Deadline t = (Deadline) o;
        return t.description.equals(this.description) && t.by.equals(this.by);
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
    @Override
    public String makeFormat() {
        return String.format("%s|%d|%s|%s\n", "D", (isDone) ? 1 :0
                ,description, this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
    }
}