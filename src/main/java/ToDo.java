/**
 * The ToDo class represents a task that has to be done.
 * It extends the Task class.
 */
public class ToDo extends Task{
    /**
     * Constructor for ToDo class.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overides toString method from Task
     * returns a String representation of ToDo task.
     *
     * @return The String representation of ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
