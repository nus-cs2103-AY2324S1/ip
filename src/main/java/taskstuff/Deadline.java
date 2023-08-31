package taskstuff;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * A class which inherits from task.Task class.
 * Represents a task with deadline but no start time.
 */
public class Deadline extends Task {

    /** The due date of this task. */
    private LocalDateTime endTime;

    /**
     * Initialises using the given description and end time.
     * @param description The name of this deadline task.
     * @param endTime The deadline of task.
     */
    public Deadline(String description, LocalDateTime endTime) {
        super(description);
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Returns a string describing this deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime.format(Parser.outputFormat) + ")";
    }
}

