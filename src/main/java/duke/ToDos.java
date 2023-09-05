package duke;

/**
 * A class for ToDos tasks.
 */
public class ToDos extends Task {
    /**
     * Constructor to initialize the ToDos class.
     *
     * @param description Describes the todos task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return String.format("T | %s | %s", doneStatus, this.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}
