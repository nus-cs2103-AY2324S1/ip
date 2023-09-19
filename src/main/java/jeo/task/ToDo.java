package jeo.task;

/**
 * Represents a task of type todo.
 *
 * @author Joseph Oliver Lim
 */
public class ToDo extends Task {

    /**
     * Constructs a todo with a specified description.
     *
     * @param description A string describing the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Compares the Todo Task to another task.
     *
     * @param o the object to be compared.
     * @return The comparison value.
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof ToDo) {
            return 0;
        }
        if (o instanceof Deadline) {
            return -1;
        }
        if (o instanceof Event) {
            return -1;
        }
        return 0;
    }
}
