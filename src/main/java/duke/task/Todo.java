package duke.task;

/**
 * The Todo class represents a task without a specific due date or time.
 *
 * @author selwyn
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     *
     * @param detail The description of the task.
     */
    public Todo(String detail) {
        super(detail);
    }

    /**
     * Returns a string representation of the Todo object, including its completion status and description.
     *
     * @return A string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
