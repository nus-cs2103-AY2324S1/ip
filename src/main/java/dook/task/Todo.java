package dook.task;

/**
 * Simple task with a description.
 */
public class Todo extends Task {
    /**
     * Instantiates a Task with given description and done status.
     * @param description Given description
     * @param isDone Given done status
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);

    }

    @Override
    public String getSaveableString() {
        return String.format("T//%s//%s", getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
