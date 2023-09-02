package ben;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadlines extends Task {
    /**
     * The deadline.
     */
    private LocalDateTime by;

    /**
     * Constructor that takes in a description and whether the deadline is completed and the date of the deadline.
     * @param description Description of deadline.
     * @param isCompleted Whether the task is completed.
     * @param by The date of the deadline.
     */
    public Deadlines(String description, boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * String representation of the deadline.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormat(by) + "HRS)";
    }

    /**
     * String representation of deadline when it is saved to the file.
     *
     * @return String representation of deadline.
     */
    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + saveDateFormat(by);
    }
}
