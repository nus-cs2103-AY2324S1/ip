package duke.task;

/**
 * Represents a todo task.
 * A todo task is a type of task that has a description and does not have a deadline or specific time.
 * It is used in a task management system to keep track of tasks without specific requirements.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description A description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string in the format: "[T] [Task Description]".
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
