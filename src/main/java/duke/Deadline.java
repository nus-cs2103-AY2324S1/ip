package duke;

/**
 * The duke.Deadline class represents a task with a deadline.
 * It extends the duke.Task class.
 * Adds a 'by' field to store the date of the deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for duke.Deadline class.
     *
     * @param description the description of the task.
     * @param by          the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides toString method from duke.Task.
     * returns a String representation of duke.Deadline task.
     *
     * @return The String representation of duke.Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Retrieves the end date of the deadline.
     *
     * @return the String representation of the end date.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Checks if the input task is equals to the current Deadline instance.
     *
     * @param newTask The task to be compared to.
     * @return True if the both task are the same.
     */
    @Override
    public boolean isDuplicate(Task newTask) {
        if (newTask instanceof Deadline) {
            Deadline addTask = (Deadline) newTask;
            return addTask.getDescription().equals(this.description)
                    && addTask.getBy().equals(this.by);
        }
        return false;
    }

    /**
     * Returns a String representation of Deadline task in file format.
     *
     * @return The String representation of task to be saved into a file.
     */
    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + by;
    }
}
