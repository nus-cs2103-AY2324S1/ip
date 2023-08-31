package monday.task;

/**
 * The ToDos class extends the Task class.
 * It contains a description and inherits the 
 * completion status functionality from the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDos object with the given description.
     *
     * @param description the description of the ToDos task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDos task,
     * including its task type indicator and description.
     *
     * @return a string representation of the ToDos task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}