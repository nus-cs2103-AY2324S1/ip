package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean isMarked) {
        super(description, "deadline", isMarked);
        this.by = by;
    }

    @Override
    public String getOriginalMessage() {
        return String.format("%s %s /by %s", this.type, this.getDescription(), this.stringifyDate(this.by));
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
