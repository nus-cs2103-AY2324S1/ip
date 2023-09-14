package tasket.task;

/**
 * The class for todo
 */
public class ToDo extends Task {

    /**
     * The constructor of todo
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * @inheritDocs
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @inheritDocs
     *
     * @return The save format of the task.
     */
    @Override
    public String toSaveString() {
        return String.format("T | %s", super.toSaveString());
    }
}
