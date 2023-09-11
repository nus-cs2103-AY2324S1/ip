package duke;

/**
 * The duke.ToDo class represents a task that has to be done.
 * It extends the duke.Task class.
 */
public class ToDo extends Task {
    /**
     * Constructor for duke.ToDo class.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overrides toString method from duke.Task
     * returns a String representation of duke.ToDo task.
     *
     * @return The String representation of duke.ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
