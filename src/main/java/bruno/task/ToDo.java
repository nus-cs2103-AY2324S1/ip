package bruno.task;

/**
 * The ToDo class is responsible for all tasks of Todo type.
 */
public class ToDo extends Task {

    public ToDo(String description, String note) {
        super(TaskType.TODO, description, note);
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
