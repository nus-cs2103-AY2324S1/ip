package alice.task;

import alice.exception.DukeException;

/**
 * Represents a task without any date/time attached to it.
 */
public class Todo extends Task {
    public static final String TASK_LABEL = "T";

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
     * Constructs a todo with the given argument.
     *
     * @param argument The argument from the command.
     * @throws DukeException If there are problems constructing the todo.
     */
    public static Todo fromArgument(String argument) throws DukeException {
        return new Todo(argument);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + TASK_LABEL + "]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return TASK_LABEL + " | " + super.toFileString();
    }
}
