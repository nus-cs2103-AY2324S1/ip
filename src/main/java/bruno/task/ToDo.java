package bruno.task;

/**
 * The ToDo class is responsible for all tasks of Todo type.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(TaskType.TODO, description);
    }

    /**
     * {@inheritDoc}
     */
    @Override public String getString() {
        return "[T]" + super.getString();
    }

    /**
     * {@inheritDoc}
     */
    @Override public String getFileString() {
        return "T|" + super.getFileString();
    }
}
