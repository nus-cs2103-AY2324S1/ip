package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

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
     * Represents Deadline task in string format.
     *
     * @return String representation of Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.dateTimeFormatter(by) + ")";
    }

    /**
     * Represents Deadline task in string format to be stored.
     *
     * @return String representation of Deadline task.
     */
    @Override
    public String storageFormat() {
        return ("D" + super.storageFormat() + " | " + by);
    }
}
