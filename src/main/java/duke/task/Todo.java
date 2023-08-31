package duke.task;

/**
 * The Todo class.
 */
public class Todo extends Task {
    /**
     * Constructor for the duke.task.Todo class.
     *
     * @param name The name of the todo task.
     * @param done Whether the task is marked done or not.
     */
    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        if (this.done) {
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
    public String displayableForm() {
        return this.toString();
    }
}
