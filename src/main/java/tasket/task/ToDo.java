package tasket.task;

/**
 * The class for todo.
 */
public class ToDo extends Task {

    /**
     * The constructor of todo.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * The constructor of todo.
     * This version of constructor includes tags.
     *
     * @param description The description of the task.
     * @param tags The tags of the task.
     */
    public ToDo(String description, String[] tags) {
        super(description, tags);
    }

    /**
     * @inheritDocs
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s %s", super.toString(), this.getTags());
    }

    /**
     * @inheritDocs
     *
     * @return The save format of the task.
     */
    @Override
    public String toSaveString() {
        return String.format("T | %s %s", super.toSaveString(), this.getTags());
    }
}
