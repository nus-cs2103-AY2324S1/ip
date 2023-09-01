package duke.tasks;

import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a "Deadline" task in the Duke application, which includes a specific deadline date and time.
 */
public class DeadlineTask extends Task {

    // The deadline date and time for the task.
    private LocalDateTime deadline;

    /**
     * Constructs a DeadlineTask with the specified name and deadline.
     *
     * @param name     The name or description of the DeadlineTask.
     * @param deadline The deadline date and time for the task.
     */
    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns a formatted description of the DeadlineTask, including the task type identifier "[D]" and the deadline.
     *
     * @return The formatted description of the DeadlineTask.
     */
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + deadline.format(Ui.DATE_OUTPUT_FORMAT) + ")";
    }

    /**
     * Converts the DeadlineTask to a string representation for storage in a file.
     *
     * @return The string representation for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + deadline.format(Ui.DATE_OUTPUT_FORMAT);
    }

    /**
     * Returns a string representation of the DeadlineTask, including the deadline.
     *
     * @return The string representation of the DeadlineTask.
     */
    @Override
    public String toString() {
        return super.toString() + " | " + deadline.format(Ui.DATE_OUTPUT_FORMAT);
    }
}
