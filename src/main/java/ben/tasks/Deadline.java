package ben.tasks;

import ben.tasks.Task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * The deadline.
     */
    private LocalDateTime by;

    /**
     * Takes in a description and whether the deadline is completed and the date of the deadline.
     * @param description Description of deadline.
     * @param isCompleted Whether the task is completed.
     * @param by The date of the deadline.
     */
    public Deadline(String description, boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Represents the deadline.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormat(by) + "HRS)";
    }

    /**
     * Represents deadline when it is saved to the file.
     *
     * @return String representation of deadline.
     */
    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + saveDateFormat(by);
    }
}
