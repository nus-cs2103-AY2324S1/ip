package seedu.duke.task;

import seedu.duke.task.Task;

import java.time.LocalDateTime;

/**
 * Represents a Deadline task.
 * This class is inherited from the Task class.
 * A Deadline task always have a description and a deadline.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * The main constructor of the Deadline task.
     *
     * @param description Description of this Deadline task.
     * @param by Deadline of this Deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        assert !super.isDone : "Task should be marked as undone initially.";
        this.by = super.parseStringToTime(by);
    }

    /**
     * Returns a string representation of this
     * Deadline task when it is saved in the
     * storage.
     *
     * @return a string representation of this Deadline task for saving.
     */
    public String toStringForSave() {
        return "D" + " | " + super.toStringForSave() + " | " + super.convertTimeForSave(this.by);
    }

    /**
     * {@inheritDoc}
     *
     * Returns a string representation of this
     * Deadline task when it is printed.
     *
     * @return a string representation of this Deadline task for printing.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.convertTimeToString(this.by) + ")";
    }
}
