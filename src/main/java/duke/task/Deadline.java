package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Task class with a deadline date (by) */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Initialize Deadline class.
     *
     * @param task Task.
     * @param by Deadline for task.
     */
    public Deadline(@JsonProperty("task") String task, @JsonProperty("by") LocalDate by) {
        super(task);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n     (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getClass() != o.getClass()) {
            return false;
        }

        boolean bothTaskAndTagEquals = super.equals(o);
        boolean bothByEquals = ((Deadline) o).by.equals(this.by);
        return bothTaskAndTagEquals && bothByEquals;
    }
}
