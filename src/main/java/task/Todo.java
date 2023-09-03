package task;

import task.Task;

/**
 * Todo is a Task that has only the description.
 */
public class Todo extends Task {

    /**
     * The constructor of Todo.
     *
     * @param description the todo description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
