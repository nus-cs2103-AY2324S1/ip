package duke.exception;

import duke.tasks.TaskType;

/**
 * Exception thrown when a task's field (description, by, from, to) is found to be empty.
 */
public class EmptyTaskException extends Exception {
    /**
     * Constructs an duke.exception.EmptyTaskException with a specific error message
     * based on the type of task and the empty field.
     *
     * @param type  The type of task (e.g., todo, deadline, event).
     * @param field The specific field (e.g., description, by, from, to) that is empty.
     */
    public EmptyTaskException(TaskType type, String field) {
        super("☹ OOPS!!! The " + field + " of a " + type + " cannot be empty.");
    }
}
