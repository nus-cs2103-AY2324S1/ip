package Remy.Task;

public class Todo extends Task{
    /**
     * Constructs a new Todo with the given description.
     * @param description The name of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns String representation of the Todo, labelled [T].
     * @return String representation of the Todo, labelled [T].
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
