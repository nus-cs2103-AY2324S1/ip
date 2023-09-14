package duke.task;

import duke.exception.DukeException;

/**
 * Represents a task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructs a todo with the given description.
     *
     * @param description The description of the todo.
     * @throws DukeException If there are problems constructing the todo.
     */
    public Todo(String description) throws DukeException {
        super(description);
    }

    /**
     * Constructs a todo with the given description and status.
     *
     * @param description The description of the todo.
     * @param isDone      The status of the todo.
     * @throws DukeException If there are problems constructing the todo.
     */
    public Todo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
