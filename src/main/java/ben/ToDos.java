package ben;

/**
 * Represents a ToDo task.
 */
public class ToDos extends Task {

    /**
     * Constructor that takes in a description and whether the Todo is completed.
     *
     * @param description Description of ToDo.
     * @param isCompleted Whether the task is completed.
     */
    public ToDos(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    /**
     * String representation of the ToDo.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * String representation of ToDo when it is saved to the file.
     *
     * @return String representation of ToDo.
     */
    @Override
    public String saveString() {
        return "T|" + super.saveString();
    }
}
