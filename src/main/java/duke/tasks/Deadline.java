package duke.tasks;

import duke.Duke;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public String getTextRepresentation() {
        return "D | " + super.getTextRepresentation() + " | " + this.by.format(Duke.TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(Duke.TIME_FORMAT) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Deadline)) {
            return false;
        } else {
            Deadline deadline = (Deadline) o;
            return deadline.by.equals(this.by)
                    && deadline.description.equals(this.description)
                    && deadline.isDone == this.isDone;
        }
    }
}
