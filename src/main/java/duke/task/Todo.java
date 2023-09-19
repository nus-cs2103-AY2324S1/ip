package duke.task;

/**
 * Represents a todo task in the chatbot application.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given name.
     *
     * @param name The name of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Converts the todo task to a string for saving.
     *
     * @return A string representation of the task for saving.
     */
    @Override
    public String toSave() {
        return (super.isComplete ? "1 " : "0 ") + "todo " + super.name;
    }

    /**
     * Converts the todo task to a string.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
