package duke.task;

/**
 * A Todo class to represent todo tasks
 */
public class Todo extends Task {

    /**
     * Constructs todo with the given description
     *
     * @param description the given description of the todo
     * @param isDone whether the todo is done
     *
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    /**
     * Check if two Todo tasks are equal.
     *
     * @param otherTask the task to compare to
     * @return true if the tasks are equal, false otherwise
     */
    @Override
    public boolean isEqual(Task otherTask) {
        if (otherTask instanceof Todo) {
            Todo otherTodo = (Todo) otherTask;
            return this.getDescription().equalsIgnoreCase(otherTodo.getDescription())
                && this.getType().equalsIgnoreCase(otherTodo.getType());
        }
        return false;
    }

    /**
     * Returns String representing the todo
     *
     * @return String representing todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
