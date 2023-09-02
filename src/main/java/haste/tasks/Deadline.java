package haste.tasks;

import haste.commands.Parser;

import java.time.LocalDateTime;

/**
 * Represents a Deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a Deadline.
     *
     * @param description Task description.
     * @param by End time of the Task.
     * @param isComplete Completion status of Task.
     */
    public Deadline(String description, LocalDateTime by, boolean isComplete) {
        super(description,isComplete);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatTime(this.by) + ")";
    }

    @Override
    public String save() {
        return "D|" + super.save() + "|" + Parser.getCmd(this.by);
    }
}
