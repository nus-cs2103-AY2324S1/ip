package duke.task;

/**
 * A class which represents
 * a task without any date/time attached to it.
 *
 * @author Andrew Daniel Janong
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo object.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toDataRepresentation() {
        return "T|" + super.toDataRepresentation();
    }

    /**
     * Returns the string representation of a ToDo.
     * Uses an extra [T] to represent a ToDo.
     *
     * @return the string representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
