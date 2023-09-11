package duke.task;

/**
 * Represents a todo.
 *
 * @author Pearlynn
 */

public class Todo extends Task {

    /**
     * Constructor for duke.task.Todo class.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description, null);
    }

    /**
     * Constructor for duke.task.Todo class.
     *
     * @param description The description of the todo.
     * @param isDone The status of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, null, isDone);
    }

    /**
     * Returns the string representation of the todo in the file.
     *
     * @return A string representation of the todo in the file.
     */
    @Override
    public String taskStringify() {
        int status = super.isDone ? 1 : 0;
        return "T | " + status + " | " + super.description;
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return A string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
