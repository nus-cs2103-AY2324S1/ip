package sisyphus.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;
    public Deadline (String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline (String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public String dateFormatter(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " +  this.description + " (by: " + dateFormatter(this.deadline) + ")";
    }

    @Override
    public String toSaveFormat() {
        return String.format("D,%s,%s,%s", this.description, this.isDone ? "1" : "0",
                this.deadline);
    }
}
