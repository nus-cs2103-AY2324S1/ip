/**
 * Represents a task without any date/time attached to it.
 *
 * @author Andrew Daniel Janong
 */
public class ToDo extends Task{
    /**
     * A public constructor for the ToDo.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * A string representation of a ToDo.
     * Uses an extra [T] to represent a ToDo.
     * @return the string representation of the ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
