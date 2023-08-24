/**
 * A ToDo class extends the task class. A Todo task is a task without a specified date to be completed.
 */
public class ToDo extends Task{

    /**
     * Constructs a new Todo task.
     *
     * @param description The name of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a description of the Todo task.
     *
     * @return A string description of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
