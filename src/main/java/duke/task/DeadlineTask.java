package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class represents a task with a deadline type in the Duke application.
 * It extends the base Task class and includes an additional field for the deadline date.
 */
public class DeadlineTask extends Task {
    private LocalDate byDate;

    /**
     * Constructs a DeadlineTask with the specified description, deadline date, and completion status.
     *
     * @param description The description of the deadline task.
     * @param byDate      The deadline date of the task.
     * @param isDone      The completion status of the task (true if done, false otherwise).
     */
    public DeadlineTask(String description, LocalDate byDate, boolean isDone) {
        super(description, isDone);
        this.byDate = byDate;
    }

    /**
     * Returns a string representation of the DeadlineTask, including task type, completion status, and description details.
     *
     * @return A string representation of the DeadlineTask.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + this.getDescriptionDetails();
    }

    /**
     * Converts the DeadlineTask to a string format suitable for saving to a file, including task type, completion status,
     * description, and deadline date.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("%s | %d | %s (by: %s)", getTaskType(), this.isDone ? 1 : 0, this.description,
                this.byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Returns the task type identifier for a deadline task (i.e., "D").
     *
     * @return The task type identifier.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns the description details of the deadline task.
     *
     * @return The deadline task name together with the by date.
     */
    private String getDescriptionDetails() {
        return this.description + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}