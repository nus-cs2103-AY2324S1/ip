package duke.task;

/**
 * Represents a task with a specific Todo in the Duke application.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task for display purposes.
     *
     * @return A formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Deadline task for writing to a file.
     * The date and time are formatted differently for file storage.
     *
     * @return A formatted string representation of the Todo task for file storage.
     */
    @Override
    public String writeFileString() {
        return super.writeFileString();
    }
}
