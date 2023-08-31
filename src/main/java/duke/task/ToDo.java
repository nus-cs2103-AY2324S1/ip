package duke.task;

/**
 * Represents a task that is to be done.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        return "[T]" + status + " " + super.description;
    }
}
