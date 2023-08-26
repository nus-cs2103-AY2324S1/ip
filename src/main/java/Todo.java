/**
 * A task to be done.
 */
public class Todo extends Task{

    /**
     * Creates a todo instance.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toStringInFile() {
        return "[T] /" + super.toStringInFile();
    }
}
