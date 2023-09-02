package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task with a specific deadline.
 * This class extends the duke.tasks.Task class and adds a deadline to it.
 */
public class Deadline extends Task {
    /**
     * The deadline for this task.
     */
    protected String by;
    protected LocalDate byDate;
    protected LocalTime byTime;
    /**
     * Constructs a duke.tasks.Deadline object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param byDate The date deadline for the task.
     * @param byTime The time deadline for the task.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
        this.by = byDate.toString() + " " + byTime.toString();
    }

    /**
     * Returns a string representation of the duke.tasks.Deadline object.
     *
     * @return A formatted string including the task type, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}