package duke.task;

/**
 * A ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo instance.
     * @param description string description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns string representation of task to be written into save file.
     * @return string representation of task
     */
    public String toWriteString() {
        return "T | " + super.toWriteString();
    }
}