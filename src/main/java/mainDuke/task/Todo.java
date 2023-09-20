package mainDuke.task;

import mainDuke.exceptions.DukeException;

/**
 * Represents a task of Todo type.
 */
public class Todo extends Task {
    /**
     * constructor, returns a todo instance.
     * @param done whether the task is done.
     * @param desc name of the task.
     * @throws DukeException if the user left the task field empty.
     */
    public Todo(boolean done, String desc) throws DukeException {
        super(done, desc.substring(5));
        if (desc.substring(5).isEmpty()) {
            throw new DukeException("You forgot to enter the task!");
        }
    }

    /**
     * String representation of item, including indication of tasktype (todo).
     * @return string representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
