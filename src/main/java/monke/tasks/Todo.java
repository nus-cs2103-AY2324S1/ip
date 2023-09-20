package monke.tasks;

/**
 * The Todo class represents a to-do task in the Monke application.
 * It extends the Task class.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, including its status icon and description.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the Todo task data for saving to a file.
     *
     * @return A formatted string representing the Todo task data.
     */
    @Override
    public String formatData() {
        return String.format("T | %d | %s\n", this.isDone ? 1 : 0, this.description);
    }
}
