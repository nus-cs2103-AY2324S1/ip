package duke.tasks;

/**
 * Represents a ToDo task.
 */
public class ToDos extends Task {
    /**
     * Constructor for todo task.
     * @param description Description of task.
     */
    public ToDos(String description) {
        super(description);
        super.taskType = 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
