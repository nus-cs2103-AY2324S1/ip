package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean marked) {
        super(description, "deadline", marked);
        this.by = by;
    }

    @Override
    public String getOriginalMessage() {
        return "deadline " + this.getDescription() + " /by " + this.stringifyDate(this.by);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.formatDate(this.by) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            return this.by.equals(d.by);
        }

        return false;
    }
}
