package avalon;

/**
 * Represents a to-do task, which is a type of task with a description and completion status.
 */
public class ToDo extends Task {

    /**
     * Creates a new to-do task with the given description and sets its initial completion status to false.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its status icon and description.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
