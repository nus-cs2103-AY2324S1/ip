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

    /**
     * Checks if the input task is equals to the current Todo instance.
     *
     * @param newTask The task to be compared to
     * @return True if the both task are the same
     */
    @Override
    public boolean isDuplicate(Task newTask) {
        if (newTask instanceof ToDo) {
            ToDo addTask = (ToDo) newTask;
            return addTask.getDescription().equals(this.description);
        }
        return false;
    }
    /**
     * Returns a String representation of Deadline task.
     *
     * @return The String representation of task to be saved into a file.
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat();
    }

}
