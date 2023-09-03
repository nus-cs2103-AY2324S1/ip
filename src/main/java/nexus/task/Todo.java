package nexus.task;

/**
 * Todo class that has description.
 */
public class Todo extends Task {
    /**
     * Creates task.
     *
     * @param description String
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        String done = this.isDone ? "1" : "0";
        return String.join("|", "T", done, this.description);
    }
}
