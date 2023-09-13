package duke.task;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a todo task with description.
     * @param description
     */
    public ToDo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
        assert this.description != null : "description should not be null";
    }

    /**
     * Constructs a todo task with description and isDone.
     * @param description
     * @param isDone
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        this.taskType = TaskType.TODO;
        assert this.description != null : "description should not be null";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toTxt() {
        return super.toTxt() + this.description;
    }

}
