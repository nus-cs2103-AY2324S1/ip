package haste.tasks;

import java.time.LocalDateTime;

import haste.commands.Parser;

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
        super(description, isComplete);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatTime(this.by) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D|" + super.toSaveFormat() + "|" + Parser.getCmdFormat(this.by);
    }
}
