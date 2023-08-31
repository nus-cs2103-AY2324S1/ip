package taskmaster.tasks;

public class Todo extends Task {

    /**
     *
     * @param description Description of todo task.
     * @param marked Whether the todo is marked or unmarked.
     */
    public Todo(String description, String marked) {
        super(description, marked);
    }

    /**
     * Returns a string representation of the todo.
     *
     * @return A string representing the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
