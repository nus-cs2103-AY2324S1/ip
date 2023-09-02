package haste.tasks;

/**
 * Represents a task.
 */
public class ToDo extends Task {
    /**
     *
     * @param description
     * @param isComplete Completion status of Task.
     */
    public ToDo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return "T|" + super.save();
    }
}

