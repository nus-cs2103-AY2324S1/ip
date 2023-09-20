package task;

/**
 * Class representing a todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified name.
     *
     * @param name The name of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a Todo task with the specified name and marked status.
     *
     * @param name   The name of the todo task.
     * @param marked The marked status of the todo task.
     */
    public Todo(String name, boolean marked) {
        super(name, marked);
    }

    @Override
    public Todo mark() {
        return new Todo(this.name, true);
    }

    @Override
    public Todo unmark() {
        return new Todo(this.name, false);
    }

    @Override
    public String saveTask() {
        return String.format("T | %s", super.saveTask());
    }

    /**
     * Checks if this task has a conflict with another task.
     * Always returns false as a conflict cannot be encountered with a todo task.
     *
     * @param t The task to check for conflicts with.
     * @return False since there are no conflicts with todo tasks.
     */
    @Override
    public boolean hasConflictWith(Task t) {
        return false;
    }

    /**
     * Returns a string representation of the Todo task, including its name.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
