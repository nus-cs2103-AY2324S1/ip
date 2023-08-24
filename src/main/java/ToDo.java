/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object. This is the main constructor of the ToDo task.
     * @param description Description of Todo.
     * @param isDone Whether ToDo is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a ToDo obkect. This calls the main constructor when the default for isDone is false.
     * @param description Description of ToDo.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Returns the string representation of the ToDo task.
     * @return string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of ToDo to be used in storage.
     * @return string representation of ToDo for storage.
     */
    @Override
    public String storageTaskRep() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
