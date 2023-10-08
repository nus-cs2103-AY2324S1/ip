package robert.task;

/**
 * An Event variation of the <tt>Task</tt> class. Used to track
 * a todo.
 *
 * @author Lee Zhan Peng
 */
public class ToDo extends Task {

    /**
     * Constructs ToDo using a description.
     *
     * @param description the description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs ToDo using a description and an indication
     * on whether it is marked.
     *
     * @param description the description of the task.
     * @param isDone whether the task is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
