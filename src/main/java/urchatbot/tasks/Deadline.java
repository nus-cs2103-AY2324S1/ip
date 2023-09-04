package urchatbot.tasks;

public class Deadline extends Task {

    protected String by;

    /**
     * Construct the Deadline class.
     *
     * @param description Description of the task.
     * @param isDone If the task is done.
     * @param by Date and/or time of the deadline.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }
    @Override
    public String toFileString() {
        if (this.isDone) {
            return "D | 1 | " + description
                    + " | " + by;
        } else {
            return "D | 0 | " + description
                    + " | " + by;
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}