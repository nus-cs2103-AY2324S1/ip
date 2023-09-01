package duke.task;

/**
 * Represents a task without any date/time attached to it.
 *
 * @author Andrew Daniel Janong
 */
public class ToDo extends Task {
    /**
     * A public constructor for the task.ToDo.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDataRepresentation() {
        return "T|" + super.getDataRepresentation();
    }

    /**
     * A string representation of a task.ToDo.
     * Uses an extra [T] to represent a task.ToDo.
     * @return the string representation of the task.ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
