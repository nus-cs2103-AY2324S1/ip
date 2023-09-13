package duke.task;

/**
 * The Todo class.
 */
public class Todo extends Task {
    /**
     * Constructor for the duke.task.Todo class.
     *
     * @param name The name of the todo task.
     * @param isDone Whether the task is marked done or not.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.name;
        } else {
            return "[T][ ] " + this.name;
        }
    }

    /**
     * Displays the todo in a user-friendly format.
     *
     * @return The string representation of the todo for display to the user.
     */
    public String userDisplayString() {
        return this.toString();
    }
}
