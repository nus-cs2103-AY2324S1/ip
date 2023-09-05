package duke.task;
/**
 * Represents a task without a specific deadline or event date.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with the given description and marks it as not done.
     *
     * @param description The description of the task.
     */
    public ToDoTask(String description) {
        super(description, false);
    }

    /**
     * Constructs a ToDoTask with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      Indicates whether the task is completed.
     */
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the ToDoTask to a string format suitable for saving.
     *
     * @return A string representation of the ToDoTask for saving to a file.
     */
    public String toSave() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDoTask, including its completion status.
     *
     * @return A string representation of the ToDoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
