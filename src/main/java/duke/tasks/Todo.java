package duke.tasks;

/**
 * Todo is a Task with no dates
 */
public class Todo extends Task{
    /**
     * Constructs a new Todo object
     * @param name Name of Todo
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Return string representation of Todo
     * @return String representation of Todo
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String generateSaveString() {
        return String.format("T | %b | %s", isDone, name);
    }
}
