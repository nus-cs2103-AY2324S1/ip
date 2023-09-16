package Kevin.Task;

public class Todo extends Task {

    private Todo(String description) {
        super(description);
    }

    /**
     * Default constructor method.
     *
     * @param description Description of this todo task.
     * @param isDone Status of this task, either done or not done.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * This is a factory method which generates a Todo task.
     *
     * @param description Description of this Todo task.
     * @return The Todo task.
     */
    public static Todo createNewTodoTask(String description) {
        return new Todo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * This method returns the string representation of this Todo task
     * in a format which can be stored in the hard disk.
     *
     * @return The string representation of this Todo task.
     */
    @Override
    public String toFileString() {
        return "[T]" + super.toString();
    }
}
