package duke.task;

import duke.util.Storage;

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
        return String.format("T%s%s\n", Storage.SEPARATOR, super.fileFormat());
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

    /**
     * Compares the current object with the given object, it returns true
     * if the given object is a todo task and has the same description.
     *
     * @param obj Object to be compared.
     * @return The result of comparison.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int compareDeadline(Task task) {
        int larger = 1;
        if (task instanceof Todo) {
            return super.compareTo(task);
        }
        return larger;
    }

    @Override
    public int compareType(Task task) {
        int smaller = -1;
        if (task instanceof Todo) {
            return super.compareTo(task);
        }
        return -1;
    }
}
