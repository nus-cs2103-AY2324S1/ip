public class Deadline extends Task {

    protected String by;

    /**
     * Constructor to build a task with description as input.
     * @param description Describes the task.
     */
    public Deadline(String description, String by) {
        this(description, false, by);
    }

    /**
     * Constructor to build a task with description and isDone as input.
     * @param description Describes the task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Prints out the description of the task and its status.
     * @return A string that shows the task's description and status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}