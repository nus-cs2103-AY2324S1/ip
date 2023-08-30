package duke.tasklist;

/**
 * Represents a Todo task.
 * This class extends the base Task class and represents a simple task without a deadline or event times.
 */
class Todo extends Task {

    /**
     * Constructs a Todo task with the given name.
     *
     * @param name The name or description of the task.
     */
    Todo(String name) {
        super(name);
    }

    /**
     * Returns the formatted string representation of the Todo task.
     *
     * @return The formatted string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
