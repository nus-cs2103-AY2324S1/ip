package duke.task;

/**
 * The ToDo class represents a task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object.
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat() + "\n";
    }
}
