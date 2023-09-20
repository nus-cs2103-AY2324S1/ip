package duke.tasks;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * Represents a Deadline task object.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new Deadline object with given description and deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Updates description of task
     *
     * @param newDescription New description.
     */
    @Override
    public void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Updates a date field of task.
     *
     * @param fieldToUpdate Field that is to be updated.
     * @param newDate New date.
     */
    @Override
    public void updateDate(String fieldToUpdate, LocalDateTime newDate) {
        this.by = newDate;
    }

    /**
     * Represents Deadline task in string format.
     *
     * @return String representation of Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatDateTime(by) + ")";
    }

    /**
     * Represents Deadline task in string format to be stored.
     *
     * @return String representation of Deadline task.
     */
    @Override
    public String toStorageFormat() {
        return ("D" + super.toStorageFormat() + " | " + by);
    }
}

