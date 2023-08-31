package duke.task;

import duke.Storage;

/**
 * Represents a todo task. A <code>Todo</code> object corresponds to
 * a todo task described by a description.
 */
public class Todo extends Task {

    /**
     * Constructs a <code>Todo</code> object with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the file format of the todo task for
     * it to be stored and read from a file.
     *
     * @return File format of the todo task.
     */
    @Override
    public String fileFormat() {
        return String.format("T%s%s", Storage.SEPARATOR, super.fileFormat());
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
