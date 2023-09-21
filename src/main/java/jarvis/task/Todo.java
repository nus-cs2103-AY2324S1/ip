package jarvis.task;

/**
 * Represents a Todo task.
 *
 * The Todo class is a type of task that only contains a description without any specific time or date associated with it.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     * The completion status of the task is set to false (i.e., not done) by default.
     *
     * @param description The description or name of the todo task.
     */
    public Todo(String description) {
        super(description, false);
    }

    /**
     * Constructs a Todo object with the specified description and completion status.
     *
     * @param description The description or name of the todo task.
     * @param isDone Indicates if the task has been completed.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the Todo object to a string format suitable for saving to a file.
     * The format includes an identifier for the task type ("T") followed by the save string of the parent Task class.
     *
     * @return A string representation of the Todo object for saving purposes.
     */
    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }

    /**
     * Returns a string representation of the Todo object.
     * The representation includes an identifier for the task type ("T") followed by the string representation of the parent Task class.
     *
     * @return A string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
