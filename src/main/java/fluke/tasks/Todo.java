package fluke.tasks;

import fluke.exceptions.FlukeException;

/**
 * A todo is a task without any dates associated with it.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo.
     * @param description Description of the task.
     * @throws FlukeException when the description given is empty.
     */
    public Todo(String description) throws FlukeException {
        super(description);
    }

    /**
     * Constructs a Todo.
     * @param description Description of the task.
     * @param isDone Whether the task is done.
     * @throws FlukeException when the description given is empty.
     */
    public Todo(String description, boolean isDone) throws FlukeException {
        super(description, isDone);
    }

    /**
     * String representation of a Todo.
     * @return a String representation of a Todo containing its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
